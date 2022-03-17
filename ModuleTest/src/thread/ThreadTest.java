package thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/3/15 11:53 上午
 */
public class ThreadTest {

    @Test
    public void testThread(){
        MyThread m1 = new MyThread("高铁");
        MyThread m2 = new MyThread("飞机");
        MyThread m3 = new MyThread("汽车");

//        m1.run();
//        m2.run();

        //线程优先级
//        System.out.println(m1.getPriority());
//        System.out.println(m2.getPriority());
//        System.out.println(m3.getPriority());

        //设置优先级--获取cpu时间片概率调整，非一定
        m1.setPriority(Thread.NORM_PRIORITY);
        m2.setPriority(Thread.MAX_PRIORITY);
        m2.setPriority(Thread.MIN_PRIORITY);

        m1.start();
        m2.start();
        m3.start();

        //获取当前线程名（静态方法）
        System.out.println(Thread.currentThread().getName());

    }


    /**
     * static Thread.sleep(ms) 让当前线程等待**ms
     */
    @Test
    public void testSleep(){
        ThreadSleep s1 = new ThreadSleep();
        ThreadSleep s2 = new ThreadSleep();
        ThreadSleep s3 = new ThreadSleep();

        s1.setName("张三");
        s2.setName("李四");
        s3.setName("王五");

        s1.start();
        s2.start();
        s3.start();

    }


    /**
     * thread.join 当前线程执行完再执行其他
     */
    @Test
    public void testJoin(){
        ThreadJoin j1 = new ThreadJoin();
        ThreadJoin j2 = new ThreadJoin();
        ThreadJoin j3 = new ThreadJoin();

        j1.setName("join1");
        j2.setName("join2");
        j3.setName("join3");

        j1.start();
        try {
            j1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        j2.start();
        j3.start();

    }


    /**
     * 标记守护线程
     *  主线程执行完后，守护线程马上结束
     */
    @Test
    public void testDaemon(){
        ThreadDaemon d1 = new ThreadDaemon();
        ThreadDaemon d2 = new ThreadDaemon();

        d1.setName("关羽");
        d2.setName("张飞");

        Thread.currentThread().setName("刘备");
        d1.setDaemon(true);
        d2.setDaemon(true);

        d1.start();
        d2.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }


    /**
     * 多线程实现方式2：实现runnable接口，本质还是依靠thread实例执行
     */
    @Test
    public void testRunnable(){
        //匿名内部类方式 ：接口不可以new
        Runnable ru = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                }
            }
        };
        Thread t1 = new Thread(ru, "张三");
        Thread t2 = new Thread(ru, "李四");

        t1.start();
        t2.start();
    }


    /**
     * 模拟三个窗口同时卖票
     */
//    @Test
//    public void testSaleTicket()
    public static void main(String args[]){

        Runnable sale = new Runnable() {
            private Object obj = new Object();
            private int num = 100;
            @Override
            public void run() {
                while (true) {
                    System.out.print(Thread.currentThread().getName());
                    synchronized (obj) {
                        if(num > 0){
                            //sleep模拟出票耗时
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(" 剩余票数：" + num --);
                        }else{
                            System.out.println(" 票卖完了");
                        }
                    }
                }
            }
        };

        Thread t1 = new Thread(sale, "窗口1");
        Thread t2 = new Thread(sale, "窗口2");
        Thread t3 = new Thread(sale, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }


    @Test
    public void testCallable(){
        Callable<String> call = new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("匿名内部类callable");
                Thread.sleep(10000);
                return "calling";
            }
        };
        FutureTask<String> futureTask = new FutureTask<>(call);

        new Thread(futureTask, "callable测试线程").start();

        String s = null;
        try {
            //此处会等待线程执行结束，主线程处于阻塞状态
            s = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(s);

    }

}
