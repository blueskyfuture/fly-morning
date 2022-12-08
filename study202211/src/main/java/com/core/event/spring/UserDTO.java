package com.core.event.spring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
public class UserDTO extends ApplicationEvent {
    private Integer userId;
    private String name;
    private Integer age;

    public UserDTO(Object source){
        super(source);
    }
}
