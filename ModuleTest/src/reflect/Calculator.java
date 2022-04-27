package reflect;

import annotation.Check;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/1/10 12:11 上午
 */
public class Calculator {

    @Check
    public void add(){
        String s = null;
        s.toString();
        System.out.println("+");
    }

    @Check
    public void sub(){
        System.out.println("-");
    }

    @Check
    public void mul(){
        String s = null;
        s.toString();
        System.out.println("*");
    }

    public void div(){
        System.out.println("/");
    }

    @Check
    public void show(){
        System.out.println("i am showing...");
    }
}
