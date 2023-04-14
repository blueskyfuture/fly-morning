package com.bluesky.tech.observerPattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ConcreteSubject extends Subject{
    private String name;
    //保存主题的状态
    private String state;

    ConcreteSubject(String name){
        this.name = name;
    }

    public void change(String newState){
        state = newState;
        log.info(name + " change state " + newState);
        //状态发生改变，通知观察者
        notifyObservers(newState);
    }
}
