package com.bluesky.tech.observerPattern;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Subject {
    //保存订阅过自己的观察者对象
    private List<MyObserver> observers = new ArrayList<>();

    //观察者对象订阅
    public void add(MyObserver observer){
        observers.add(observer);
        log.info("add an observer");
    }

    //观察者对象注销
    public void remove(MyObserver observer){
        observers.remove(observer);
        log.info("remove an observer");
    }

    //通知所有注册的观察者对象
    public void notifyObservers(String newState){
        for (MyObserver observer : observers) {
            observer.update(newState);
        }
    }

}
