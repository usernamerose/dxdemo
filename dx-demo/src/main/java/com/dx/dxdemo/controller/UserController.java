package com.dx.dxdemo.controller;

import com.dx.dxdemo.dao.User;
import com.dx.dxdemo.repoisitory.UserRepoisitory;
import com.dx.dxdemo.utils.DXUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author tWX579831
 * @date 2020/3/19 23:05
 */
@RestController()
@RequestMapping("/user")
public class UserController {
    private static Map<String, String> telCodeMap = new HashMap<>();
    @Autowired
    private UserRepoisitory userRepoisitory;

    @GetMapping("/findAll/{pageSize}/{currentPage}")
    private Object findAll(@PathVariable("pageSize") Integer pageSize, @PathVariable("currentPage") Integer currentPage) {
        int total = userRepoisitory.findAll().size();
        int totalPage = Integer.valueOf(new DecimalFormat("######0").format(Math.ceil(total * 1.0 / pageSize)));
        PageRequest of = PageRequest.of(currentPage - 1, pageSize, Sort.Direction.ASC, "id");
        List<User> ecsMsgs = userRepoisitory.findUserByPage(of).getContent();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("totalNum", total);
        stringObjectHashMap.put("totalPage", totalPage);
        stringObjectHashMap.put("data", ecsMsgs);
        return stringObjectHashMap;
    }

    /**
     * 根据手机号查询用户是否已经注册
     *
     * @param telnum
     * @return
     */
    @GetMapping("/isRegistered/{telnum}")
    private Map<String, Boolean> isRegistered(@PathVariable("telnum") Long telnum) {
        HashMap<String, Boolean> result = new HashMap<>();
        User user = userRepoisitory.findByTelnum(telnum);
        if (user == null) {
            result.put("result", false);
        } else {
            result.put("result", true);
        }
        return result;
    }

    /**
     * 编辑用户查看手机号是否可用
     *
     * @param telnum
     * @return
     */
    @GetMapping("/isAvaliable/{telnum}/{userId}")
    private Map<String, Boolean> isAvaliable(@PathVariable("telnum") Long telnum, @PathVariable("userId") Integer userId) {
        HashMap<String, Boolean> result = new HashMap<>();
        Optional<User> byId = userRepoisitory.findById(userId);
        if (byId.get().getTelnum().equals(telnum)) {
            result.put("result", true);
        } else {
            List<User> users = userRepoisitory.findAllByTelnum(telnum);
            if (users.size() > 0) {
                result.put("result", false);
            } else {
                result.put("result", true);
            }
        }
        return result;
    }


    /**
     * 用户注册 传入手机号和密码
     *
     * @param user
     * @return
     */
    @PostMapping("/regist")
    private Map<String, String> regist(@RequestBody User user) {
        HashMap<String, String> result = new HashMap<>();
        User checkUser = userRepoisitory.findByTelnum(user.getTelnum());
        if (checkUser != null) {
            result.put("result", "fail");
            result.put("msg", "该手机号已经注册了！");
            return result;
        }
        userRepoisitory.save(user);
        result.put("result", "success");
        return result;
    }

    /**
     * 登录完成后根据手机号查询用户基本信息详情
     *
     * @param telnum
     * @return
     */
    @GetMapping("/findByTelnum/{telnum}")
    private Map<String, Object> findByTelnum(@PathVariable("telnum") Long telnum) {
        HashMap<String, Object> result = new HashMap<>();
        User user = userRepoisitory.findByTelnum(telnum);
        if (user == null) {
            result.put("result", "fail");
            result.put("msg", "用户不存在或已被删除");
        } else {
            result.put("result", "success");
            result.put("msg", user);
        }
        return result;
    }

    /**
     * 管理侧，用户侧更新用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    private Map<String, String> updateUser(@RequestBody User user) {
        HashMap<String, String> result = new HashMap<>();
        User checkUser = userRepoisitory.findByTelnum(user.getTelnum());
        userRepoisitory.save(user);
        result.put("result", "success");
        return result;
    }


    /**
     * 上传文件
     */
    @PostMapping("/upload/{type}/{userId}")
    public Object uploadUserFile(@PathVariable("type") String type, @PathVariable("userId") Integer userId, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        try {
            User user = userRepoisitory.findById(userId).get();
            String base64FileCode = DXUtils.encodeBase64File(multipartFile.getBytes());
            if ("idCard".equals(type)) {
                user.setIdCardBase64(base64FileCode);
            } else if ("bank".equals(type)) {
                user.setBankCardBase64(base64FileCode);
            } else if ("photo".equals(type)) {
                user.setPhotoBase64(base64FileCode);
            } else {
                return new HashMap<String, String>() {{
                    put("result", "请正确选择上传图片的类型");
                }};
            }
            userRepoisitory.save(user);
            return new HashMap<String, String>() {{
                put("result", "success");
            }};
        } catch (Exception e) {
            return new HashMap<String, String>() {{
                put("result", "图片太大，请选重新选择");
            }};
        }

    }


    /**
     * 根据用户ID删除用户
     */
    @GetMapping("/delete/{userId}")
    public Object deleteById(@PathVariable Integer userId) {
        userRepoisitory.deleteById(userId);
        return new HashMap<String, String>() {{
            put("result", "success");
        }};
    }

    /**
     * 用户登录，传入手机号和密码
     *
     * @param telnum
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        User existUser = userRepoisitory.findByTelnumAndPassword(user.getTelnum(), user.getPassword());
        return new HashMap<String, Object>() {{
            put("result", existUser == null ? "用户名或密码不正确" : existUser);
        }};
    }


    /**
     * 发送验证码
     */
    @GetMapping("/sendTelCode/{telnum}")
    public Object sendTelCode(@PathVariable("telnum") String telnum) {
        String ramdomCode = String.valueOf(new Random().nextInt(999999));
        telCodeMap.put(telnum, ramdomCode);
        DXUtils.sendMessageToTel(telnum, ramdomCode);
        return new HashMap<String, String>() {{
            put("result", "success");
        }};
    }

    /**
     * 验证验证码
     */
    @GetMapping("/checkTelCode/{telnum}/{telCode}")
    public Object checkTelCode(@PathVariable("telnum") String telnum, @PathVariable("telCode") String telCode) {
        String s = telCodeMap.get(telnum);
        if (StringUtils.isNotBlank(s)) {
            boolean equals = s.equals(telCode);
            return new HashMap<String, String>() {{
                put("result", equals ? "success" : "验证码不正确");
            }};
        } else {
            return new HashMap<String, String>() {{
                put("result", "没请求验证码");
            }};
        }
    }


}
