package com.bluesky.tech.observerPattern;

public class ObserverpatternDemo {
    public static void main(String[] args) {
        //第一步：创建主题
        ConcreteSubject mSubject = new ConcreteSubject("中心主题");
        //第二步：创建观察者
        MyObserver myObserverA = new MyConcreteObserver("订阅者A");
        MyObserver myObserverB = new MyConcreteObserver("订阅者B");
        //主题订阅
        mSubject.add(myObserverA);
        mSubject.add(myObserverB);
        //第四步：主题状态变更
        mSubject.change("倒计时结束，开始秒杀");
    }

}
