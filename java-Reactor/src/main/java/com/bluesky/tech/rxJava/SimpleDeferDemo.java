package com.bluesky.tech.rxJava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Observable;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SimpleDeferDemo {
    @Test
    public void deferDemo(){
        AtomicInteger foo = new AtomicInteger(100);
        Observable observable = Observable.just(foo.get());

        //延迟创建
        Observable delayObservable = Observable.defer(()->Observable.just(foo.get()));
        //修改对象的值
        foo.set(200);

        //有观察者订阅
        observable.subscribe(i -> log.info("just emit:{}", i));
        //有观察者订阅
        delayObservable.subscribe(i -> log.info("defer just emit:{}",i));
    }
}
