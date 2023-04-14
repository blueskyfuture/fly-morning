package com.bluesky.tech.rxJava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CreaterOperatorDemo {
    /**
     * just使用
     */
    @Test
    public void justDemo(){
        Observable.just("hello")
                .subscribe(s -> log.info("s==" + s));
        Observable.just(1,2,3,4,5,6,7,8,9,10)
                .subscribe(i -> log.info("i==" + i));
    }

    @Test
    public void fromDemo(){
        String[] items = {"a","b","c","d","e"};
        Observable.from(items).subscribe(s->log.info("item==" + s));

        Integer[] arr = {1,2,3,4,5};
        List<Integer> list = Arrays.asList(arr);
        Observable.from(list)
                .subscribe(i -> log.info("i==" + i));
    }

    @Test
    public void rangeDemo(){
        Observable.range(1,10)
                .subscribe(i -> log.info("i==" + i));
    }

    @Test
    public void intervalDemo() throws InterruptedException{
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribe(i -> log.info("i==" + i));
        Thread.sleep(Integer.MAX_VALUE);
    }

}
