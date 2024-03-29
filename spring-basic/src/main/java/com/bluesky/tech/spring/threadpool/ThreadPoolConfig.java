package com.bluesky.tech.spring.threadpool;

import java.util.concurrent.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 参考https://blog.csdn.net/qq_24983911/article/details/94722569
 * 使用默认异步执行线程池，只需在Application.java增加@EnableAsync
 * 使用手动设置线程池，需要增加该config
 */
//@Configuration
//@EnableAsync // 允许使用异步方法
public class ThreadPoolConfig {

    @Bean
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        threadPoolTaskExecutor.setCorePoolSize(5);
        // 设置最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(5);
        // 设置工作队列大小
        threadPoolTaskExecutor.setQueueCapacity(2000);
        // 设置线程名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix("threadPoolTaskExecutor-->");
        // 设置拒绝策略.当工作队列已满,线程数为最大线程数的时候,接收新任务抛出RejectedExecutionException异常
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化线程池
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean
    public Executor myThreadPool() {
        // 设置核心线程数
        int corePoolSize = 5;
        // 设置最大线程数
        int maxPoolSize = 5;
        // 设置工作队列大小
        int queueCapacity = 2000;
        // 最大存活时间
        long keepAliveTime = 30;
        // 设置线程名称前缀
        String threadNamePrefix = "myThreadPool-->";
        // 设置自定义拒绝策略.当工作队列已满,线程数为最大线程数的时候,接收新任务抛出RejectedExecutionException异常
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new RejectedExecutionException("自定义的RejectedExecutionHandler");
            }
        };
        // 自定义线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            private int i = 1;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName(threadNamePrefix + i);
                i++;
                return thread;
            }
        };
        // 初始化线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueCapacity),
                threadFactory, rejectedExecutionHandler);
        return threadPoolExecutor;
    }

    @Bean
    public Executor myThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new MyThreadPoolTaskExecutor();
        // 设置核心线程数
        threadPoolTaskExecutor.setCorePoolSize(5);
        // 设置最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(5);
        // 设置工作队列大小
        threadPoolTaskExecutor.setQueueCapacity(2000);
        // 设置线程名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix("myThreadPoolTaskExecutor-->");
        // 设置拒绝策略.当工作队列已满,线程数为最大线程数的时候,接收新任务抛出RejectedExecutionException异常
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化线程池
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }


}
