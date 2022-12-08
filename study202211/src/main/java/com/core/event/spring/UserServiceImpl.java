package com.core.event.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Override
    public void register(){
        UserDTO userDTO = new UserDTO(this);
        userDTO.setAge(18);
        userDTO.setName("admol");
        userDTO.setUserId(1001);
        System.out.println("register user");
        eventPublisher.publishEvent(userDTO);
    }
}
