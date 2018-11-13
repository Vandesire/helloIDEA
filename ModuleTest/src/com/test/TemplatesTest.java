package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nameisempty
 * @create 2018-11-13 13:46
 */
public class TemplatesTest {
    public static void main(String[] args) {

        int num1 = 2;
        System.out.println("num1 = " + num1);


        String[] arr = new String[]{"Tom","Honny","Lilei"};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        for (String s : arr) {
            System.out.println(s);
        }

        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            System.out.println(s);
        }

        List list = new ArrayList();
        list.add(222);
        list.add(111);
        list.add(333);

        list.remove(Integer.valueOf(222));

        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static final int num = 2;

    public void test1(){

        System.out.println("TemplatesTest.test1");
    }


}
