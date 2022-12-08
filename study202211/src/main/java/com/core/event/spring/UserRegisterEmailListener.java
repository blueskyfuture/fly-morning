package com.core.event.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// 通过实现接口实现监听器
@Component
public class UserRegisterEmailListener implements ApplicationListener<UserDTO> {
    @Override
    public void onApplicationEvent(UserDTO userDTO){
        System.out.println("监听到用户注册，准备发送邮件，user:" + userDTO.toString());
    }
}
