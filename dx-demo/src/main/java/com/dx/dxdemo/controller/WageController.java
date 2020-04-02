package com.dx.dxdemo.controller;

import com.dx.dxdemo.dao.User;
import com.dx.dxdemo.dao.Wage;
import com.dx.dxdemo.repoisitory.UserRepoisitory;
import com.dx.dxdemo.repoisitory.WageRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * @author tWX579831
 * @date 2020/3/22 14:17
 */
@RestController
@RequestMapping("/wage")
public class WageController {
    @Autowired
    private WageRepository wageRepository;
    @Autowired
    private UserRepoisitory userRepoisitory;

    /**
     * 用户id和年份查询
     */
    @GetMapping("/findByUserIdAndYear/{year}/{telnum}")
    public Object findByUserIdAndYear(@PathVariable("year") Integer year, @PathVariable("telnum") Long telnum) {
        User byTelnum = userRepoisitory.findByTelnum(telnum);
        List<Wage> wages = wageRepository.findByYearAndUserId(year, byTelnum.getId());

        return wages;
    }

    @PostMapping("/update")
    public Object update(@RequestBody Wage wage) {
        wageRepository.save(wage);
        return new HashMap<String, String>() {
            {
                put("result", "success");
            }
        };
    }

    @GetMapping("/delete/{wageId}")
    public Object delete(@PathVariable("wageId") Integer wageId) {
        wageRepository.deleteById(wageId);
        return new HashMap<String, String>() {
            {
                put("result", "success");
            }
        };
    }

    /**
     * 导出
     *
     * @param response
     * @param userId
     * @throws IOException
     */
    @GetMapping("/export/{userId}/{year}")
    public void export(HttpServletResponse response, @PathVariable("userId") Integer userId, @PathVariable("year") Integer year) throws IOException {
        List<Wage> byUserId = wageRepository.findByUserIdAndYear(userId, year);
        Workbook workbook = new XSSFWorkbook();
        User user = userRepoisitory.findById(userId).get();
        Sheet sheet = workbook.createSheet("日账单");
        //设置首行标题标题
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("年份");
        row.createCell(1).setCellValue("月份");
        row.createCell(2).setCellValue("基本工资（单位：元）");
        row.createCell(3).setCellValue("绩效,奖金等其他工资组成");
        row.createCell(4).setCellValue("社保,税收等扣费项");
        row.createCell(5).setCellValue("是否发放");
        row.createCell(6).setCellValue("反馈意见");
        row.createCell(7).setCellValue("审批意见");
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (Wage wage : byUserId) {
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(wage.getYear());
            row.createCell(1).setCellValue(wage.getMonth());
            row.createCell(2).setCellValue(wage.getBaseWage());
            row.createCell(3).setCellValue(wage.getOtherWage());
            row.createCell(4).setCellValue(wage.getDeduction());
            row.createCell(5).setCellValue(wage.isProvide() ? "已发放" : "未发放");
            row.createCell(6).setCellValue(wage.getFeedBackInfo());
            row.createCell(7).setCellValue(wage.getApprovalInfo());
            rowNum++;
        }

        File file = new File("file.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream("file.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        workbook.close();

        response.setHeader("Content-type", "application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(user.getName() + "工资详情.xlsx", "utf-8"));
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(fileOutputStream);
        }

        if (file.exists()) {
            file.delete();
        }

    }
}
