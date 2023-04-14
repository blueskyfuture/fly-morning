package com.bluesky.tech.rxJava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Emitter;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

@Slf4j
public class BackpressureDemo {

    /**
     * 使用背压
     */
    @Test
    public void testBackPressure(){
        //被观察者(主题),主题实例，使用背压
        Observable observable = Observable.create(new Action1<Emitter<String>>() {
            @Override
            public void call(Emitter<String> emitter) {
//                for (int i = 0; i < 10; i++) {
                    for (int i = 0;; i++) {
                    log.info(Thread.currentThread().getName() + "---produce ->" + i);
                    emitter.onNext(String.valueOf(i));
                }
            }
        },Emitter.BackpressureMode.LATEST);
        //观察者
        Action1<String> subscriber = new Action1<String>() {
            @Override
            public void call(String s) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);//毫秒
                }catch (InterruptedException ex){
                    log.info("ex:{}",ex.getMessage());
                }
                log.info(Thread.currentThread().getName() + "consumer->" + s);
            }
        };

        //订阅：Observable与Subscriber之间依然通过subscribe()进行关联Observable
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(subscriber);

        try {
//            TimeUnit.MILLISECONDS.sleep(10*1000);//毫秒
            TimeUnit.MILLISECONDS.sleep(60*60*1000);//毫秒
//            TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE*1000);//毫秒
        }catch (InterruptedException ex){
            log.info("ex:{}",ex.getMessage());
        }
    }

    /**
     * 不使用背压
     */
    @Test
    public void testNoBackPressure(){
        //被观察者(主题)
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 10; i++) {
                    log.info(Thread.currentThread().getName() + "---produce ->" + i);
                    subscriber.onNext(String.valueOf(i));
                }
            }
        });
        //观察者
        Action1<String> subscriber = new Action1<String>() {
            @Override
            public void call(String s) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);//毫秒
                }catch (InterruptedException ex){
                    log.info("ex:{}",ex.getMessage());
                }
                log.info(Thread.currentThread().getName() + "consumer->" + s);
            }
        };

        //订阅：Observable与Subscriber之间依然通过subscribe()进行关联Observable
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(subscriber);

        try {
            TimeUnit.MILLISECONDS.sleep(10*1000);//毫秒
//            TimeUnit.MILLISECONDS.sleep(60*60*1000);//毫秒
//            TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE*1000);//毫秒
        }catch (InterruptedException ex){
            log.info("ex:{}",ex.getMessage());
        }
    }


    /**
     * 不使用背压
     */
    @Test
    public void testNoBackPressure1(){
        //被观察者(主题)
        Observable observable = Observable.create((Subscriber<? super String> subscriber)->{
            for (int i = 0; i < 10; i++) {
//              for (int i = 0; i<Integer.MAX_VALUE; i++) {
//              for (int i = 0; ; i++) {
                log.info(Thread.currentThread().getName() + "---produce ->" + i);
                subscriber.onNext(String.valueOf(i));
            }
        });
        //观察者
        Action1<String> subscriber = (String s)->{
          try {
              TimeUnit.MILLISECONDS.sleep(100);//毫秒
          }catch (InterruptedException ex){
              log.info("ex:{}",ex.getMessage());
          }
          log.info(Thread.currentThread().getName() + "consumer->" + s);
        };

        //订阅：Observable与Subscriber之间依然通过subscribe()进行关联Observable
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(subscriber);

        try {
            TimeUnit.MILLISECONDS.sleep(10*1000);//毫秒
//            TimeUnit.MILLISECONDS.sleep(60*60*1000);//毫秒
//            TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE*1000);//毫秒
        }catch (InterruptedException ex){
            log.info("ex:{}",ex.getMessage());
        }
    }
}
