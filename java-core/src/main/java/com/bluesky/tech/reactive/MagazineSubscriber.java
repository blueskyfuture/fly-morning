package com.bluesky.tech.reactive;

import java.util.concurrent.Flow;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参考https://www.jianshu.com/p/ce207be13078
 * class中实现了必要的方法如下：
 *
 * onSubscriber(subscription) Publisher在被指定一个新的Subscriber时调用此方法。 一般来说你需要在subscriber内部保存这个subscrition实例，因为后面会需要通过她向publisher发送信号来完成：请求更多数据，或者取消订阅。 一般在这里我们会直接请求第一个数据，正如代码中所示。
 * onNext(magazineNumber) 每当新的数据产生，这个方法会被调用。在我们的示例中，我们用到了最经典的使用方式：处理这个数据的同时再请求下一个数据。然而我们在这中间添加了一段可配置的sleep时间，这样我们可以尝试订阅者在不同场景下的表现。剩下的一段逻辑判断仅仅是记录下丢失的杂志（当publisher出现丢弃数据的时候）。
 * onError(throwable) 当publisher出现异常时会调用subscriber的这个方法。在我们的实现中publisher丢弃数据时会产生异常。
 * onComplete() 当publisher数据推送完毕时会调用此方法，于是整个订阅过程结束。
 *
 */
public class MagazineSubscriber implements Flow.Subscriber<Integer> {

    public static final String JACK = "Jack";
    public static final String PETE = "Pete";

    private static final Logger log = LoggerFactory.
            getLogger(MagazineSubscriber.class);

    private final long sleepTime;
    private final String subscriberName;
    private Flow.Subscription subscription;
    private int nextMagazineExpected;
    private int totalRead;

    MagazineSubscriber(final long sleepTime, final String subscriberName) {
        this.sleepTime = sleepTime;
        this.subscriberName = subscriberName;
        this.nextMagazineExpected = 1;
        this.totalRead = 0;
    }

    @Override
    public void onSubscribe(final Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(final Integer magazineNumber) {
        if (magazineNumber != nextMagazineExpected) {
            IntStream.range(nextMagazineExpected, magazineNumber).forEach(
                    (msgNumber) ->
                            log("Oh no! I missed the magazine " + msgNumber)
            );
            // Catch up with the number to keep tracking missing ones
            nextMagazineExpected = magazineNumber;
        }
        log("Great! I got a new magazine: " + magazineNumber);
        takeSomeRest();
        nextMagazineExpected++;
        totalRead++;

        log("I'll get another magazine now, next one should be: " +
                nextMagazineExpected);
        subscription.request(1);
    }

    @Override
    public void onError(final Throwable throwable) {
        log("Oops I got an error from the Publisher: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log("Finally! I completed the subscription, I got in total " +
                totalRead + " magazines.");
    }

    private void log(final String logMessage) {
        log.info("<=========== [" + subscriberName + "] : " + logMessage);
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    private void takeSomeRest() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
