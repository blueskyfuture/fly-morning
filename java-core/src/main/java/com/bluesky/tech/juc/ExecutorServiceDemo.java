package com.bluesky.tech.juc;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 参考：https://blog.csdn.net/qq_24983911/article/details/94722569
 * 线程池的合理参数
 * 生产环境中如何配置 corePoolSize 和 maximumPoolSize
 *
 * 这个是根据具体业务来配置的，分为CPU密集型和IO密集型
 *
 * CPU密集型
 * CPU密集的意思是该任务需要大量的运算，而没有阻塞，CPU一直全速运行
 * CPU密集任务只有在真正的多核CPU上才可能得到加速（通过多线程）
 *
 * 而在单核CPU上，无论你开几个模拟的多线程该任务都不可能得到加速，因为CPU总的运算能力就那些
 *
 * CPU密集型任务配置尽可能少的线程数量：
 *
 * 一般公式：CPU核数 + 1个线程数
 *
 * IO密集型
 * 由于IO密集型任务线程并不是一直在执行任务，则可能多的线程，如 CPU核数 * 2
 *
 * IO密集型，即该任务需要大量的IO操作，即大量的阻塞
 *
 * 在单线程上运行IO密集型的任务会导致浪费大量的CPU运算能力花费在等待上
 *
 * 所以IO密集型任务中使用多线程可以大大的加速程序的运行，即使在单核CPU上，这种加速主要就是利用了被浪费掉的阻塞时间。
 *
 * IO密集时，大部分线程都被阻塞，故需要多配置线程数：
 *
 * 参考公式：CPU核数 / (1 - 阻塞系数) 阻塞系数在0.8 ~ 0.9左右
 *
 * 例如：8核CPU：8/ (1 - 0.9) = 80个线程数
 */
public class ExecutorServiceDemo {
    private final ExecutorService executors = new ThreadPoolExecutor(16, 32,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            //new ThreadPoolExecutor.AbortPolicy());
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) {
        ExecutorServiceDemo demo = new ExecutorServiceDemo();
        demo.test01();
    }

    public void test01(){
        //List<String> strings = Arrays.asList("a", "b", "c");
        List<String> strings = new ArrayList<>();
        //i=31,34,40,100,200
        for (int i = 1; i < 100 ; i++) {
            strings.add("key_"+i);
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("==1=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        List<Future<String>> futureList = new ArrayList<>();
        strings.stream().forEach(x -> {
            Task task = new Task(x);
            Future<String> result = executors.submit(task);
            futureList.add(result);
        });
        System.out.println("==2=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        List<String> list = new ArrayList<>();
        /**
         * 并发执行查询任务
         */
        Lock lock = new ReentrantLock();
        futureList.parallelStream().forEachOrdered(result -> {
            try {
                String data = result.get();
                lock.lock();
                list.add(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        System.out.println("==3=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
        list.stream().forEach(x -> System.out.println(x));
        System.out.println("==4=="+stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }


    /**
     * 任务处理
     */
    public class Task implements Callable<String> {
        private String str;

        public Task(String str) {
            this.str = str;
        }

        @Override
        public String call() throws Exception {
            return taskHandle(str);
        }
    }

    private String taskHandle(String str){
        String result = "";
        //线程睡眠指定时间
        try {
            System.out.println(Thread.currentThread().getName()+"==="+str);
            TimeUnit.MILLISECONDS.sleep(1000);
            result = "handle "+str;
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return result;
    }
}
