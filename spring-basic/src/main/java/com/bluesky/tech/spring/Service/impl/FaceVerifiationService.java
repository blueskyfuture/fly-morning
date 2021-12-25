package com.bluesky.tech.spring.Service.impl;

import com.bluesky.tech.spring.handle.vo.Rsres;
import org.springframework.stereotype.Service;

/**
 * 模拟人脸识别服务
 */
@Service
public class FaceVerifiationService {

    public Rsres<String> getResult(String userInfo) {
        Rsres<String> faceVerificationResult = new Rsres<String>();
        // 成功返回 100
        faceVerificationResult.setReturnCode(100);
        faceVerificationResult.setReturnMsg("success");
        faceVerificationResult.setResult(userInfo);
        try {
            // 模拟调用人脸识别接口的http请求消耗的时间
            //Thread.sleep(1000);
            Thread.sleep(1000);//6000;7000;7200-err
            // 如果需要模拟异常的情况那么时间应该在
            // 4(主方法的逻辑处理时间) + 3(允许的超时时间) 也就是7s以上才可以
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return faceVerificationResult;
    }
}


