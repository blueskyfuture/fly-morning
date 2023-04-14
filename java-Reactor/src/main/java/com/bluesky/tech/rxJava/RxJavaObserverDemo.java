package com.bluesky.tech.rxJava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

@Slf4j
public class RxJavaObserverDemo {

    /**
     * 演示RxJava中的Observer模式
     */
    @Test
    public void rxJavaBaseUse(){
        //被观察者（主题）
        Observable observable = Observable.create(
                new Action1<Emitter<String>>() {
                    @Override
                    public void call(Emitter<String> emitter) {
                        emitter.onNext("apple");
                        emitter.onNext("banana");
                        emitter.onNext("pear");
                        emitter.onCompleted();
                    }
                },Emitter.BackpressureMode.NONE
        );
        //订阅者（观察者）
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log.info("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("onError");
            }

            @Override
            public void onNext(String s) {
                log.info("onNext：{}", s);
            }
        };

        //订阅：Observable与Subscriber之间依然通过subscrible()进行关联
        observable.subscribe(subscriber);
    }

    /**
     * 演示RxJava中的不完整观察者
     */
    @Test
    public void rxJavaActionDemo(){
        //被观察者（主题）
        Observable observable = Observable.create(
                new Action1<Emitter<String>>() {
                    @Override
                    public void call(Emitter<String> emitter) {
                        emitter.onNext("apple");
                        emitter.onNext("banana");
                        emitter.onNext("pear");
                        emitter.onCompleted();
                    }
                },Emitter.BackpressureMode.NONE
        );

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                log.info(s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                log.info("onError,Error Info is :{}",throwable.getMessage());
            }
        };
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                log.info("onCompleted");
            }
        };
        log.info("第1次订阅：");
        //根据onNextAction来定义onNext()
        observable.subscribe(onNextAction);

        log.info("第2次订阅：");
        //根据onNextAction来定义onNext();根据onErrorAction来定义onError()
        observable.subscribe(onNextAction,onErrorAction);

        log.info("第3次订阅：");
        //根据onNextAction来定义onNext();根据onErrorAction来定义onError();根据onCompletedAction来定义onCompleted()
        observable.subscribe(onNextAction,onErrorAction,onCompletedAction);
    }

    /**
     * 演示RxJava中的Lamda表达式
     */
    @Test
    public void rxJavaActionLamda(){
        Observable<String> observable = Observable.just("s1","s2","s3");
        log.info("第1次订阅：");
        //使用Action1函数式接口来实现onNext回调
        observable.subscribe(s -> log.info(s));

        log.info("第2次订阅：");
        //使用Action1函数式接口来实现onNext回调
        observable.subscribe(
                s -> log.info(s),
                e -> log.info("onError,Error Info is :{}",e.getMessage()));

        log.info("第3次订阅：");
        //使用Action1函数式接口来实现onNext回调
        observable.subscribe(
                s -> log.info(s),
                e -> log.info("onError,Error Info is :{}",e.getMessage()),
                () -> log.info("onCompleted弹射结束"));
    }

}
