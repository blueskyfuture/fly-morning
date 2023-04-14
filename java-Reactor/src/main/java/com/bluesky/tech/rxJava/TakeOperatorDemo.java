package com.bluesky.tech.rxJava;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Observable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TakeOperatorDemo {

    /**
     * 演示take操作符
     * 这是一个10秒倒计时实例
     * @throws InterruptedException
     */
    @Test
    public void takeDemo(){
        //格式化
        DateTimeFormatter fmTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        //当前时间
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(fmTime);

        log.info("now:{}", time);
        Observable.interval(1, TimeUnit.SECONDS)//设置时间间隔
                .take(10)//10秒倒计时
                .map(i -> 10 -i)
                .subscribe(j -> {
                    //获取当前时间
                    LocalTime cur = LocalTime.now();
                    System.out.println(cur);
                    log.info("res=" + j);
                });
        try {
            TimeUnit.MILLISECONDS.sleep(100*1000);//毫秒
        }catch (InterruptedException ex){
            log.info("ex:{}",ex.getMessage());
        }

    }
}
