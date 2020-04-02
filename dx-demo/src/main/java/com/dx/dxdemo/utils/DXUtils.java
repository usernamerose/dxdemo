package com.dx.dxdemo.utils;

import com.github.qcloudsms.*;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class DXUtils {


    private static Base64.Encoder encoder = Base64.getEncoder();

    public static String encodeBase64File(byte[] buffer) throws Exception {
        return encoder.encodeToString(buffer);
    }


    public static void sendMessageToTel(String telephoneNumber, String code) {
        // 短信应用SDK AppID
        int appid = 1400337993; // 1400开头

        // 短信应用SDK AppKey
        String appkey = "192e1da0b967acde29c9c02edcf67f76";

        // 需要发送短信的手机号码
        String[] phoneNumbers = {telephoneNumber};

        int templateId = 559945;

        String smsSign = "盘胖翻身记";

        // 指定模板ID单发短信
        try {
            String[] params = {code};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
//        // 短信应用SDK AppID
//        int appid = 1400337993; // 1400开头
//
//        // 短信应用SDK AppKey
//        String appkey = "192e1da0b967acde29c9c02edcf67f76";
//
//        // 需要发送短信的手机号码
//        String[] phoneNumbers = {"17794240832"};
//
//        // 短信模板ID，需要在短信应用中申请
//        // NOTE: 这里的模板ID`7839`只是一个示例，
//        // 真实的模板ID需要在短信控制台中申请
//        int templateId = 558449;
//
//        // 签名
//        // NOTE: 这里的签名"腾讯云"只是一个示例，
//        // 真实的签名需要在短信控制台中申请，另外
//        // 签名参数使用的是`签名内容`，而不是`签名ID`
//        String smsSign = "盘胖翻身记";
//
////        // 单发短信
////        try {
////            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
////            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers[0],
////                "【腾讯云】您的验证码是: 5678", "", "");
////            System.out.print(result);
////        } catch (HTTPException e) {
////            // HTTP响应码错误
////            e.printStackTrace();
////        } catch (JSONException e) {
////            // json解析错误
////            e.printStackTrace();
////        } catch (IOException e) {
////            // 网络IO错误
////            e.printStackTrace();
////        }
//
//        // 指定模板ID单发短信
//        try {
//            String[] params = {"941019"};
//            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
//            SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers,
//                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//            System.out.print(result);
//        } catch (HTTPException e) {
//            // HTTP响应码错误
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // json解析错误
//            e.printStackTrace();
//        } catch (IOException e) {
//            // 网络IO错误
//            e.printStackTrace();
//        }
//
//        // 拉取短信回执以及回复
//        try {
//            // Note: 短信拉取功能需要联系腾讯云短信技术支持(QQ:3012203387)开通权限
//            int maxNum = 10;  // 单次拉取最大量
//            SmsStatusPuller spuller = new SmsStatusPuller(appid, appkey);
//
//            // 拉取短信回执
//            SmsStatusPullCallbackResult callbackResult = spuller.pullCallback(maxNum);
//            System.out.println(callbackResult);
//
//            // 拉取回复
//            SmsStatusPullReplyResult replyResult = spuller.pullReply(maxNum);
//            System.out.println(replyResult);
//        } catch (HTTPException e) {
//            // HTTP响应码错误
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // json解析错误
//            e.printStackTrace();
//        } catch (IOException e) {
//            // 网络IO错误
//            e.printStackTrace();
//        }
//
//        // 拉取单个手机短信状态
//        try {
//            int beginTime = 1511125600;  // 开始时间(unix timestamp)
//            int endTime = 1511841600;    // 结束时间(unix timestamp)
//            int maxNum = 10;             // 单次拉取最大量
//            SmsMobileStatusPuller mspuller = new SmsMobileStatusPuller(appid, appkey);
//
//            // 拉取短信回执
//            SmsStatusPullCallbackResult callbackResult = mspuller.pullCallback("86",
//                phoneNumbers[0], beginTime, endTime, maxNum);
//            System.out.println(callbackResult);
//
//            // 拉取回复
//            SmsStatusPullReplyResult replyResult = mspuller.pullReply("86",
//                phoneNumbers[0], beginTime, endTime, maxNum);
//            System.out.println(replyResult);
//        } catch (HTTPException e) {
//            // HTTP响应码错误
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // json解析错误
//            e.printStackTrace();
//        } catch (IOException e) {
//            // 网络IO错误
//            e.printStackTrace();
//        }
    }
}