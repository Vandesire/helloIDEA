package thread;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/3/15 2:15 下午
 */
public class ThreadJoin extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " : " + i);
        }
    }
}
