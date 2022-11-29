package com.bluesky.pattern.masterWorker;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MasterWorkerTest {
    static class SimpleTask extends Task<Integer>{

        @Override
        protected Integer doExecute() {
            System.out.println("task " + getId() + "is done ");
            return getId();
        }
    }


    public static void main(String[] args) {
        //创建Master,包含4个Worker，并启动Master的执行线程
        Master<SimpleTask, Integer> master = new Master<>(4);
        //定期向master提交任务
        Executors.newScheduledThreadPool(2).scheduleAtFixedRate(() -> master.submit(new SimpleTask()), 1, 2,TimeUnit.SECONDS);
        //定期从master提取结果
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> master.printResult(), 1, 5,TimeUnit.SECONDS);

    }
}
