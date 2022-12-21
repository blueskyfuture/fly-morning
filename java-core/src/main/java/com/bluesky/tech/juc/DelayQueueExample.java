package com.bluesky.tech.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {
        public static void main(String[] args) {
            Random random = new Random(47);
            ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
            DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();

            // 初始化 delayQueue ，
            for (int i = 0; i < 20; i++) {
                delayQueue.put(new DelayedTask(random.nextInt(5000)));
            }
            delayQueue.add(new DelayedTask.EndSentinel(5000, newCachedThreadPool));

            // 启动任务开关
            newCachedThreadPool.execute(new DelayedTaskConsumer(delayQueue));
        }
    }
    class DelayedTask implements Runnable, Delayed {
        private static int counter = 0 ;
        private final int id = counter ++ ;
        private final int delta ;
        private final long trigger ;
        protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

        public DelayedTask(int delayInMilliseconds) {
            this.delta = delayInMilliseconds ;
            // 从较细粒度到较粗粒度的舍位转换，这样会失去精确性。例如，将 999 毫秒转换为秒的结果为 0。
            // 使用参数从较粗粒度到较细粒度转换，如果参数为负，则在数字上溢出至 Long.MIN_VALUE，
            // 如果为正，则为 Long.MAX_VALUE。 将1毫秒转换为毫微秒
            trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(this.delta, TimeUnit.MILLISECONDS);
            sequence.add(this) ;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed delayed) {
            DelayedTask task = (DelayedTask) delayed ;
            if (this.delta < task.delta) return -1 ;
            if (this.delta > task.delta) return 1 ;
            return 0;
        }

        @Override
        public void run() {
            System.out.println(this + " ");
        }

        @Override
        public String toString() {
            return String.format("[%1$-4d]", delta) + " Task:" + id;
        }

        public String summary () {
            return "(" + id + ":" + delta + ")";
        }


        public static class EndSentinel extends DelayedTask {

            private ExecutorService exec ;

            public EndSentinel(int delayInMilliseconds, ExecutorService exec) {
                super(delayInMilliseconds);
                this.exec = exec ;
            }

            @Override
            public void run() {
                for (DelayedTask task : sequence) {
                    System.out.print(task.summary() + " ");
                }
                System.out.println();
                System.out.println(this + " Calling shutdownNow()");
                exec.shutdownNow() ;
            }

        }
    }
    class DelayedTaskConsumer implements Runnable {

        private DelayQueue<DelayedTask> q ;

        public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
            this.q = q ;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //注意：手动调用run方法
                    q.take().run();
                }
            } catch (InterruptedException e) {}
            System.out.println("Finish DelayedTaskConsumer");
        }
    }
