package com.bluesky.pattern.masterWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import javax.swing.text.TabableView;

public class Master<T extends Task, R> {
    //所有Worker的集合
    private HashMap<String, Worker<T,R>> workers = new HashMap<>();
    //任务的集合
    private LinkedBlockingQueue<T> taskQueue = new LinkedBlockingQueue<>();
    //任务处理结果集合
    protected Map<String, R> resultMap = new ConcurrentHashMap<>();
    //Master的任务调度线程
    private Thread thread = null;
    //保持最终的和
    private AtomicLong sum = new AtomicLong(0);

    public Master(int workerCount){
        //每个worker对象都需要持有queue的引用，用于领任务与提交结果
        for (int i = 0; i < workerCount; i++) {
            Worker<T,R>  worker = new Worker<>();
            workers.put("node_" + i,worker);
        }
        thread = new Thread(() -> this.execute());
        thread.start();
    }


    //提交任务
    public void submit(T task){
        taskQueue.add(task);
    }

    //获取worker结果处理的回调函数
    private void resultCallBack(Object o){
        Task<R> task = (Task<R>) o;
        String taskName = "Worker:" + task.getWorkerId() + "-" + "Task:" + task.getId();
        R result = task.getResult();
        resultMap.put(taskName, result);
        sum.getAndAdd((Integer)result);
    }


    private void execute() {
        for (; ;){
            //从任务队列中获取任务，然后Worker节点查询，轮流分配任务
            for (Map.Entry<String,Worker<T,R>> entry : workers.entrySet()) {
                T task = null;
                try {
                    task = this.taskQueue.take();//获取任务
                    Worker worker = entry.getValue();//获取节点
                    worker.submit(task, this::resultCallBack);//分配任务
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


    //获取最终结果
    public void printResult(){
        System.out.println("-------sum is:" + sum.get());
//        for (Map.Entry<String, R> entry : resultMap.entrySet()) {
//            String taskName = entry.getKey();
//            System.out.println(taskName + ":" + entry.getValue());
//        }
    }


}
