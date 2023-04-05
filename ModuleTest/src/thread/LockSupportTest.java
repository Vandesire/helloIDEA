package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2023/2/12 10:51
 */
public class LockSupportTest {

    private static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        char[] chars = "abcd".toCharArray();

        char[] nums = "1234".toCharArray();

        t1 = new Thread(() -> {
            for (char c :chars){
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char num : nums) {
                LockSupport.park();
                System.out.println(num);
                LockSupport.unpark(t1);
            }

        }, "t2");

        t2.start();
        t1.start();


    }


}
