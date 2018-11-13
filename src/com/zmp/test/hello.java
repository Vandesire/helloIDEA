package com.zmp.test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 这里是文档说明
 */
public class hello {
    private String details;
    private Integer prize;
    private Integer num;
    private Date created;

    public hello(String details, Integer prize, Integer num, Date created) {
        this.details = details;
        this.prize = prize;
        this.num = num;
        this.created = created;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public static void main(String[] args) {
        System.out.println("hello IDEA");

        List list = new ArrayList<String>();

    }

    public void f1() {
        getList();


        try {
            FileInputStream fis = new FileInputStream("hello.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void getList() {
        ArrayList<String> list = new ArrayList<>();

        Date date = new Date();

        list.add("dfadfaf");


    }


}
