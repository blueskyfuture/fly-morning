package com.bluesky.tech.rxJava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WindowDemo {

    /**
     * 演示window创建操作符创建滚动窗口
     */
    @Test
    public void simpleWindowObserverDemo(){
        List<Integer> srcList = Arrays.asList(10,11,20,21,30,31);
        Observable.from(srcList)
                .window(3)//以固定数量分组
                .flatMap(o -> o.toList())
                .subscribe(list -> log.info(list.toString()));
    }

    /**
     * 演示window创建操作符创建滑动窗口
     */
    @Test
    public void windowObserverDemo(){
        List<Integer> srcList = Arrays.asList(10,11,20,21,30,31);
        Observable.from(srcList)
                .window(3,1)
                .flatMap(o -> o.toList())
                .subscribe(list -> log.info(list.toString()));
    }

    /**
     * 演示window创建操作符创建时间窗口
     */
    @Test
    public void timeWindowObserverDemo(){
        Observable eventStream = Observable.interval(100, TimeUnit.MILLISECONDS);
        eventStream.window(300,TimeUnit.MILLISECONDS)
                .flatMap(o -> ((Observable<Integer>)o).toList())
                .subscribe(list -> log.info(list.toString()));
        try {
            TimeUnit.MILLISECONDS.sleep(20*1000);//毫秒
        }catch (InterruptedException ex){
            log.info("ex:{}",ex.getMessage());
        }
    }


}
