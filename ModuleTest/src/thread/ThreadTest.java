package thread;

import com.alibaba.druid.support.json.JSONUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
     * **不带返回值
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
    @Test
    public void testSaleTicket(){
//    public static void main(String args[]){

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


    /**
     * 带返回值的线程
     */
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


    /**
     * 线程池测试
     */
    @Test
    public void testThreadPool(){

        //runnableExecute
        List<Runnable> runnableExecuteList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = String.valueOf(i);
            Runnable runnable = new Runnable() {

                @Override
                public void run(){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("runnable_execute: " + s);
                }
            };
            runnableExecuteList.add(runnable);
        }

        //runnableSubmit
        List<Runnable> runnableSubmitList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = String.valueOf(i);
            Runnable runnable = new Runnable() {

                @Override
                public void run(){
                    System.out.println("runnable_submit: " + s);
                }
            };
            runnableSubmitList.add(runnable);
        }

        //callable
        List<Callable> callableList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String s = String.valueOf(i);
            Callable<String> call = new Callable() {

                @Override
                public Object call() throws Exception {
                    Thread.sleep(1000);
                    return "calling: " + s;
                }
            };
            callableList.add(call);
        }

        //默认线程工厂，拒绝策略：丢弃任务
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        System.out.println("------------execute()_runnable----------");
        for (Runnable runnable : runnableExecuteList) {
            threadPool.execute(runnable);
        }

        System.out.println("------------submit()_runnable-----------");
        for (Runnable runnable : runnableSubmitList) {
            Future<?> future = threadPool.submit(runnable);
            //get会阻塞当前主线程
//            try {
//                System.out.println(future.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }

        //关闭线程池-- 等待任务执行结束
        System.out.println("--------线程池关闭------");
//        threadPool.shutdown();

        //无等待立即关闭，并返回未执行任务
        List<Runnable> noRunnableList = threadPool.shutdownNow();
        System.out.println("noRunList.size: " + noRunnableList.size());

        System.out.println("------------submit()_callable-----------");
        for (Callable callable : callableList) {
            Future future = threadPool.submit(callable);
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}
