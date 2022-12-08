package com.core.event.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMessageListener implements ApplicationListener<UserDTO> {
    @Override
    public void onApplicationEvent(UserDTO userDTO){
        System.out.println("监听到用户注册，给新用户发送首条站内短消息，user:" + userDTO.toString());
    }
}
