package com.bluesky.tech.observerPattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MyConcreteObserver implements MyObserver{
    private String name;
    //观察者状态
    private String observerState;

    MyConcreteObserver(String name){
        this.name = name;
    }

    @Override
    public void update(String newState) {
        //更新观察者状态，与它与主题的状态一致
        observerState = newState;
        log.info(name + ">>>观察者的当前状态为：" + observerState);
    }
}
