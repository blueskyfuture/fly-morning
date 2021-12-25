package com.bluesky.tech.spring.Service.impl;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.bluesky.tech.spring.handle.vo.Rsres;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 * 模拟登录验证服务
 * 当用户登录的时候,需要去数据库查询用户的账号状态做一些业务逻辑的判断,同时也需要去进行在线人脸识别.
 * 只有两个校验都通过的时候才会成功登陆.
 * 这个时候我们就可以用异步的调用在线人脸识别的接口,从而加快系统的响应时间.
 */
@Service
public class LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    FaceVerifiationService faceVerifiationService;

    @Autowired
    ApplicationContext applicationContext;

    public Rsres<String> login(String userInfo) {
        Rsres<String> rsres = new Rsres<String>();
        LoginService loginService = applicationContext.getBean(LoginService.class);
        //异步执行人脸识别
        Future<Rsres<String>> onlineFaceVerificationResult = loginService.getOnlineFaceVerificationResult(userInfo);
        //调用数据库验证
        try {
            // 模拟从数据获取数据判断用户账号状态是否冻结的消耗时间
            logger.info("check by db begin");
            Thread.sleep(4000);
            logger.info("check by db end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Rsres<String> faceVerificationResult = null;
        try {
            // 如果3秒还没有结果直接人脸识别失败
            faceVerificationResult = onlineFaceVerificationResult.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("获取人脸识别结果失败");
            // 异常处理
        }
        if (faceVerificationResult != null) {
            rsres.setReturnCode(faceVerificationResult.getReturnCode());
            rsres.setReturnMsg(faceVerificationResult.getReturnMsg());
            rsres.setResult(faceVerificationResult.getResult());
        }
        return rsres;
    }

    /**
     * 异步调用人脸识别服务
     * @param userInfo
     * @return
     */
    @Async
    public Future<Rsres<String>> getOnlineFaceVerificationResult(String userInfo) {
        logger.info("异步执行getOnlineFaceVerificationResult begin :" + System.currentTimeMillis());
        logger.info("异步执行getOnlineFaceVerificationResult threadName:{}",Thread.currentThread().getName());
        Rsres<String> result = faceVerifiationService.getResult(userInfo);
        logger.info("异步执行getOnlineFaceVerificationResult end :" + System.currentTimeMillis());
        logger.info("异步执行getOnlineFaceVerificationResult result:{},time:{}",result.getReturnCode(),System.currentTimeMillis());
        return new AsyncResult<>(result);
    }
}

