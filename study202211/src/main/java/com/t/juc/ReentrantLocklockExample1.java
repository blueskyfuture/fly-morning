package com.t.juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class WorkerClassLL implements Runnable {
    String nm;
    ReentrantLock relc;

    public WorkerClassLL(ReentrantLock rl, String n) {
        relc = rl;
        nm = n;
    }

    public void run() {
        System.out.println("WorkerClassLL name=" + nm);
        boolean dn = false;
        while (!dn) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Outer Lock
            //boolean ans = relc.tryLock();
            boolean ans = true;
            //阻塞未获取到线程的锁
            relc.lock();

            System.out.println("WorkerClassLL name=" + nm + ",ans=" + ans);
            //  True if lock is free
            if (ans) {
                try {
                    Thread.sleep(5000);
                    /**
                    int i = 0;
                    for (i = 0; i <= 6; i++) {
                        if (i >= 2) {
                            relc.lock();
                            Thread t = new Thread();
                            System.out.println("Thread Created.....");
                            if (i == 3) {
                                t.setName("Maint Thread2");
                                System.out.println("Thread Created setName.....");
                            }
                        }
                        if (i == 4) {
                            relc.unlock();
                            System.out.println("relc.unlock.....");
                        }
                        break;
                    }
                     **/
                    System.out.println(" after sleep(1500) Is locked - " + nm + " work done--" + relc.isLocked());
                    System.out.println("task name - " + nm + " work done");
                    dn = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    relc.unlock();
                }
            }
        }
    }

}

public class ReentrantLocklockExample1 {
    static final int MAX_Time = 2;
//    static int MAX_Time = 2;

    public static void main(String[] args) {
        ReentrantLock rel = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(MAX_Time);
        Runnable wrk1 = new WorkerClassLL(rel, "Job1");
        pool.execute(wrk1);
        Runnable wrk2 = new WorkerClassLL(rel, "Job2");
        pool.execute(wrk2);
        pool.shutdown();
    }
}
