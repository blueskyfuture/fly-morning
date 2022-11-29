package com.bluesky.tech.reactive;

/**
 * 参考:https://blog.csdn.net/zssrxt/article/details/104109893
 */
public class CountStart {

    public static void main(String[] args)throws Exception {
        CountPublisher publisher = new CountPublisher(500);
        CountSubscriber subscriber = new CountSubscriber(15);
        publisher.subscribe(subscriber);
        Thread.currentThread().join();
    }
}
