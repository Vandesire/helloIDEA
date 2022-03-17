package thread;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/3/15 11:54 上午
 */
public class MyThread extends Thread{

    MyThread(){};

    MyThread(String name) { super(name);}


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }
}
