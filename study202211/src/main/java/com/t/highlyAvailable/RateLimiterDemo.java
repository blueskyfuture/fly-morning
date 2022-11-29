package com.t.highlyAvailable;


//import com.google.common.util.concurrent.RateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;

/**
 * https://javaguide.cn/high-availability/limit-request.html
 **/
public class RateLimiterDemo {

    public static void main(String[] args) {
        test1();

        test2();
    }

    private static void test1() {
        // 1s 放 5 个令牌到桶里也就是 0.2s 放 1个令牌到桶里
        RateLimiter rateLimiter = RateLimiter.create(5);
        for (int i = 0; i < 10; i++) {
            double sleepingTime = rateLimiter.acquire(1);
            System.out.printf("get 1 tokens: %ss%n", sleepingTime);
        }
    }

    private static void test2() {
            // 1s 放 5 个令牌到桶里也就是 0.2s 放 1个令牌到桶里
            // 预热时间为3s,也就说刚开始的 3s 内发牌速率会逐渐提升到 0.2s 放 1 个令牌到桶里
            RateLimiter rateLimiter = RateLimiter.create(5, 3, TimeUnit.SECONDS);
            for (int i = 0; i < 20; i++) {
                double sleepingTime = rateLimiter.acquire(1);
                System.out.printf("get 1 tokens: %sds%n", sleepingTime);
            }
    }


}