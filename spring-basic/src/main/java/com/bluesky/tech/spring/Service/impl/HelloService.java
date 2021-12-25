package com.bluesky.tech.spring.Service.impl;

import com.bluesky.tech.spring.handle.vo.Rsres;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class HelloService {

    Logger logger = LoggerFactory.getLogger(HelloService.class);

    /**
     * @Async标注的方法，称之为异步方法；这些方法将在执行的时候，
     * 将会在独立的线程中被执行，调用者无需等待它的完成，即可继续其他的操作。
     */
    @Async
//    @Async("threadPoolTaskExecutor")
    //@Async("myThreadPool")
//    @Async("myThreadPoolTaskExecutor")
    public void sayHello() throws InterruptedException {
        logger.info("start say hello");
        logger.info(Thread.currentThread().getName());
        TimeUnit.MICROSECONDS.sleep(100);
        logger.info("hello");
        logger.info("end say hello");
    }

    @Async("myThreadPoolTaskExecutor")
    public Future<Rsres<String>> getHelloString(String name)throws InterruptedException {
        logger.info("start getHelloString");
        Rsres<String> ret = new Rsres<String>();
        ret.setResult("Say hello to "+name);
        logger.info(Thread.currentThread().getName());
        TimeUnit.MICROSECONDS.sleep(100);
        logger.info("end getHelloString");
        return new AsyncResult<>(ret);
    }

}
