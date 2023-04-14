package com.bluesky.tech.rxJava;

//import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import rx.Observable;
import rx.Subscriber;
//import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SchedulerDemo {
    /**
     * 演示Schedules的基本使用
     */
    @Test
    public void testScheduler(){
        //被观察者
        Observable observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        for (int i = 0; i < 5; i++) {
                            log.info(Thread.currentThread().getName() + "---produce ->" + i);
                            subscriber.onNext(String.valueOf(i));
                        }
                        subscriber.onCompleted();
                    }
                });

        //订阅Observable与Subscriber之间依然通过subscribe()进行关联Observable

        observable
                .subscribeOn(Schedulers.io())//改变弹射的线程，使用具有线程缓存机制的可复用线程
                .observeOn(Schedulers.newThread())//改变订阅的线程，每执行一个任务创建一个新的线程
                .subscribe(s -> {
                    log.info(Thread.currentThread().getName() + "----consumer ->" + s );
        });
        try {
            TimeUnit.MILLISECONDS.sleep(10*1000);//毫秒
        }catch (InterruptedException ex){
            log.info("ex:{}",ex.getMessage());
        }


    }
}
