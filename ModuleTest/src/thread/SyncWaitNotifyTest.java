package thread;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2023/2/2 18:00
 *
 * 两个线程交替打印出a1b2c3...z26
 * 思路：sync控制交替运行，flag标签控制线程执行优先级
 *
 */
public class SyncWaitNotifyTest {

    private static String[] strings = {"a","b","c","d"};

    private static int[] ints = {1, 2, 3};

    //控制t1先行
    private static boolean isT1Run = false;

    public static void main(String[] args) {

        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            isT1Run = true;
            synchronized (lock) {
                for (int i = 0; i < strings.length; i++) {
                    System.out.println(strings[i]);
                    //释放锁 & 当前线程进入等待
                   notifyAndWait(lock);
                }
                lock.notify();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                if(!isT1Run) {
                    notifyAndWait(lock);
                }
                for (int i = 0; i < ints.length; i++) {
                    System.out.println(ints[i]);
                    notifyAndWait(lock);
                }
                lock.notify();
            }
        }, "t2");

        t2.start();
        t1.start();

    }

    static void notifyAndWait(Object lock){
        lock.notify();
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
