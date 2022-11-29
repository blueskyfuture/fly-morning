package com.bluesky.tech.reactive;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * magazineDeliveryExample中我们为两个不同的subscribers设置了两个不同的等待时间， 并且设置了缓存容量maxStorageInPO
 *
 * 步骤如下：
 * 1、创建SubmissionPublisher并设置一个标准的线程池（每个subscriber拥有一个线程）
 * 2、创建两个subscribers，通过传递变量设置不同的消费时间和不同的名字，以在log中方便区别
 * 3、用20个数字的的stream数据集作为数据源以扮演“杂志打印机”，我们调用offer，并传递以下变量：
 * a. 提供给subscribers的数据。
 * b. 第二和第三个变量是等待subscribers获取杂志的最大时间。
 * c. 控制器以处理数据丢弃的情况。这里我们抛出了一个异常，返回false意味着告诉publisher不需要重试。
 * 4、当丢弃数据发生时，offer方法返回一个负数，否则将返回publisher的最大容量（以供最慢的subscriber消费），同时打印这个数字。
 * 5、 最后我们添加了一个循环等待以防止主进程过早结束。这里一个是等待publisher清空缓存数据，另外等待最慢的subscriber收到onComplete回调信号（close()调用之后）
 *
 *
 * main()方法中使用不同参数调用以上逻辑三次，以模拟之前介绍的三种不同的场景
 * 1、消费者消费速度很快，publisher缓存区不会发生问题。
 * 2、其中一个消费者速度很慢，以至缓存被填满，然而缓存区足够大以容纳所有所有数据，不会发生丢弃。
 * 3、其中一个消费者速度很慢，同时缓存区不够大，这是控制器被触发了多次，subscriber没有收到所有数据。
 *
 * 链接：https://www.jianshu.com/p/ce207be13078
 */
public class ReactiveFlowApp {

    private static final int NUMBER_OF_MAGAZINES = 20;
    private static final long MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE = 2;
    private static final Logger log =
            LoggerFactory.getLogger(ReactiveFlowApp.class);

    public static void main(String[] args) throws Exception {
        final ReactiveFlowApp app = new ReactiveFlowApp();

        log.info("\n\n### CASE 1: Subscribers are fast, buffer size is not so " +
                "important in this case.");
        app.magazineDeliveryExample(100L, 100L, 8);

        log.info("\n\n### CASE 2: A slow subscriber, but a good enough buffer " +
                "size on the publisher's side to keep all items until they're picked up");
        app.magazineDeliveryExample(1000L, 3000L, NUMBER_OF_MAGAZINES);

        log.info("\n\n### CASE 3: A slow subscriber, and a very limited buffer " +
                "size on the publisher's side so it's important to keep the slow " +
                "subscriber under control");
        app.magazineDeliveryExample(1000L, 3000L, 8);

    }

    /**
     *
     * @param sleepTimeJack Jack睡眠时间
     * @param sleepTimePete Pete睡眠时间
     * @param maxStorageInPO  最大容量
     * @throws Exception
     */
    void magazineDeliveryExample(final long sleepTimeJack,
            final long sleepTimePete,
            final int maxStorageInPO) throws Exception {
        final SubmissionPublisher<Integer> publisher =
                new SubmissionPublisher<>(ForkJoinPool.commonPool(), maxStorageInPO);

        final MagazineSubscriber jack = new MagazineSubscriber(
                sleepTimeJack,
                MagazineSubscriber.JACK
        );
        final MagazineSubscriber pete = new MagazineSubscriber(
                sleepTimePete,
                MagazineSubscriber.PETE
        );

        publisher.subscribe(jack);
        publisher.subscribe(pete);

        log.info("Printing 20 magazines per subscriber, with room in publisher for "
                + maxStorageInPO + ". They have " + MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE +
                " seconds to consume each magazine.");
        IntStream.rangeClosed(1, 20).forEach((number) -> {
            log.info("Offering magazine " + number + " to consumers");
            final int lag = publisher.offer(
                    number,
                    MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE,
                    TimeUnit.SECONDS,
                    (subscriber, msg) -> {
                        subscriber.onError(
                                new RuntimeException("Hey " + ((MagazineSubscriber) subscriber)
                                        .getSubscriberName() + "! You are too slow getting magazines" +
                                        " and we don't have more space for them! " +
                                        "I'll drop your magazine: " + msg));
                        return false; // don't retry, we don't believe in second opportunities
                    });
            if (lag < 0) {
                log("Dropping " + -lag + " magazines");
            } else {
                log("The slowest consumer has " + lag +
                        " magazines in total to be picked up");
            }
        });

        // Blocks until all subscribers are done (this part could be improved
        // with latches, but this way we keep it simple)
        while (publisher.estimateMaximumLag() > 0) {
            Thread.sleep(500L);
        }

        // Closes the publisher, calling the onComplete() method on every subscriber
        publisher.close();
        // give some time to the slowest consumer to wake up and notice
        // that it's completed
        Thread.sleep(Math.max(sleepTimeJack, sleepTimePete));
    }

    private static void log(final String message) {
        log.info("===========> " + message);
    }

}
