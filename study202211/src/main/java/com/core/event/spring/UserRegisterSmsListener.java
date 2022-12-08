package com.core.event.spring;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterSmsListener{

    // 通过注解实现监听器
    @EventListener
    public void handleUserEvent(UserDTO userDTO){
        System.out.println("监听到用户注册，准备发送短信，user:"+userDTO.toString());
    }
}
