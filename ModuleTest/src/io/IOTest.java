package io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/3/23 15:19
 *
 * IO测试
 * 划分1：Input/Output
 * 划分2: 字节流（操作文件.mp3,.mp4）/字符流（操作文本——中文乱码.java,.txt）
 *      InputStream/OutputStream
 *      Reader/Writer
 *
 */
public class IOTest {


    /**
     * 文件字节流Input测试_1 基础api测试
     * FileInputStream/FileOutputStream
     */
    @Test
    public void testFileI_base(){
        FileInputStream fileInputStream = null;
        try {
//            URL resource = IOTest.class.getClassLoader().getResource("123.txt");
             fileInputStream = new FileInputStream("123.txt");
            int i ;
            byte[] b = new byte[3];
//            while (i != -1) {
//                //每次读取一个字节，性能差，不可避免中文乱码
//                i = fileInputStream.read();
//                System.out.print((char) i);
//            };
            //每次读取一个字节数组，性能提升，中文乱码
            while ((i = fileInputStream.read(b)) != -1) {
                for (int x = 0; x < i; x++) {
                    System.out.print((char) b[x]);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 文件字节流Input测试_2 乱码问题
     * 方法1：定义一个字节数组与文件大小一样，一次性读取(文件较大时内存溢出问题)
     * 方法2：直接使用官方inputStream api：（jdk9以上版本）
     *      public byte[] readAllBytes() throws IOException
     *      java.nio.file.Files#readAllBytes(java.nio.file.Path)
     */
    @Test
    public void testFileI_code(){

//        URL resource = IOTest.class.getClassLoader().getResource("123.txt");
        FileInputStream fileInputStream = null;
        try {
            File file = new File("123.txt");
            fileInputStream = new FileInputStream(file);
            long length = file.length();
            byte[] by = new byte[(int) length];
            int num = fileInputStream.read(by);
            String s = new String(by, "utf-8");
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 文件字节流output测试
     */
    @Test
    public void testFileOut(){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("1.txt");
            String s = "12345";
            fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
            //先将一部分数据写上去
            fileOutputStream.flush();
            fileOutputStream.write("abc".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 文件字符输入流reader测试
     * FileReader
     */
    @Test
    public void testFileReader(){

        FileReader fileReader = null;

        try {
            fileReader = new FileReader("123.txt");
            int read;
            //读取一个字符
//            while ((read = fileReader.read()) != -1) {
//                System.out.print((char)read);
//            };
            //读取一个字符数组
            char[] chars = new char[4];
            //注意覆盖数组元素
            while ((read = fileReader.read(chars)) != -1){
                for (int i = 0; i < read; i++) {
                    System.out.print(chars[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 字符缓冲流测试
     * BufferedReader/BufferedWriter
     */
    @Test
    public void testBufferReader(){
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("123.txt"));
//            int len;
//            char[] chars = new char[1024];
//            while((len = bufferedReader.read(chars)) != -1) {
//                for (int i = 0; i < len; i++) {
//                    System.out.print(chars[i]);
//                }
//            }
            String s = null;
            //一次读取一行，不含回车
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 字符转换流 charset
     * InputStreamReader/OutputStreamWriter
     *
     */


    /**
     * 序列化/反序列化 对象字节输出流
     * ObjectInputStream/ObjectOutputStream
     */
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("序列化.txt")));
            oos.writeObject(new String("序列化测试！！！！"));
            oos.close();

            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("序列化.txt")));
            Object o = ois.readObject();
            System.out.println(o.toString());
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
