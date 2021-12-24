package com.bluesky.tech.thread;

/**
 * 多线程实现几个模板
 */
public class TreadDemo {

    public static void main(String[] args) {
        TreadDemo demo = new TreadDemo();
        demo.test01();
        //demo.test02();
        //使用第三种方式，类实例化开启线程（继承方式)
        new ThreadTest().start();
        //使用第四种方式，类实例化开启线程（实现方式)
        new Thread(new RunnableTest()).start();
    }

    private void test01() {
        //使用第一种方式，开启线程
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "......方式1==" + i);
                }

            }
        }.start();


    //使用第二种方式，开启线程
    Runnable r = new Runnable() {
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ".....方式2==" + i);
            }
        }
    };
    new Thread(r).start();
}

    private void test02() {
        MyThread thread1=new MyThread("ThreadA");
        MyThread thread2=new MyThread("ThreadB");
        MyThread thread3=new MyThread("ThreadC");

        new Thread(thread1).start();
        new Thread(thread2).start();
        new Thread(thread3).start();
    }


}

class ThreadTest extends Thread
{
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            System.out.println(Thread.currentThread().getName()+".....类的方式(extends)----"+ i);
        }
    }

}

class  RunnableTest implements Runnable
{
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            System.out.println(Thread.currentThread().getName()+".........类的方式(implements)----++"+ i);
        }
    }
}


class MyThread implements Runnable{
    private String name;
    public MyThread(String name){
        this.name=name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(this.name+"==>"+i);
        }
    }
}
