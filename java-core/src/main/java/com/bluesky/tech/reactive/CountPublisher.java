package com.bluesky.tech.reactive;

import java.util.concurrent.Flow;

public class CountPublisher implements Flow.Publisher<Integer> {
        private int count = 0;          //需要的计数值
        private final long interval;    //发送间隔
        private boolean isCanceled;     //是否被取消

        public CountPublisher(long interval) {
            this.interval = interval;
            isCanceled = false;
        }

        @Override
        public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
            new Thread(() -> {
                try {
                    subscriber.onSubscribe(new CountSubscription());
                    for (int i = 0; i < count && !isCanceled; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(interval);
                    }
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }).start();
        }

        private class CountSubscription implements Flow.Subscription {
            @Override
            public void request(long n) {
                count += n;//根据请求数新增计数值
            }

            @Override
            public void cancel() {
                isCanceled = true;//将标志位置为已取消
            }
        }
    }

