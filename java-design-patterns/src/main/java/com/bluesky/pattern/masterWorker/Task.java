package com.bluesky.pattern.masterWorker;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Task<R> {
    static AtomicInteger index = new AtomicInteger(1);
    //任务的回调函数
    public Consumer<Task<R>> resultAction;
    //任务的id
    private int id;
    //worker id
    private int workerId;
    //计算结果
    R result = null;
    public Task(){
        this.id = index.getAndIncrement();
    }

    public void execute(){
        this.result = this.doExecute();
        //执行回调函数
        resultAction.accept(this);
    }

    //由子类实现
    protected R doExecute(){
        return null;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getWorkerId(){
        return workerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }
}
