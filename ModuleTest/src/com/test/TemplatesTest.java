package com.test;

import java.util.*;

/**
 * @author Nameisempty
 * @create 2018-11-13 13:46
 */
public class TemplatesTest {

    public static void main(String[] args) {

        String in = "abbccddde";

        //顺序非重复list
        List<String> list = new ArrayList<>();
        //map统计，顺序
        Map<String, Integer> zipMap = new HashMap<>();

        //次数统计
        int counts  = 0;
        for (int i = 0; i < in.length(); i++) {
            String s = in.substring(i, i + 1);
            Integer count = zipMap.get(s);
            //未set
            if(count == null){
                zipMap.put(s, 1);
                list.add(s);
            }else {
                //已set
                count += 1;
                zipMap.put(s, count);
            }

        }

        StringBuilder result = new StringBuilder();
        list.forEach( x -> {
            Integer count = zipMap.get(x);
            result.append(count).append(x);
        });

        System.out.println(result.toString());
    }


}
