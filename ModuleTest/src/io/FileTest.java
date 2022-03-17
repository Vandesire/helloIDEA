package io;

import org.junit.Test;

import java.io.File;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/3/3 1:30 下午
 */
public class FileTest {


    @Test
    public void fileListTest(){

        File file = new File("/Users/mac/IdeaProjects/helloIDEA");
        listFile(file);
    }

    /**
     * 递归遍历所有文件
     */
    public void listFile(File file){
        if(!file.exists()) {
            return;
        }

        File[] files = file.listFiles();
        if(null == files){
            return;
        }

        for (File f : files) {
            if(f.isFile()){
                System.out.println(f.getAbsolutePath() + " " + f.getName());
            }
            //递归
            if(f.isDirectory()){
                listFile(f);
            }
        }
    }

    @Test
    public void fileRemoveTest(){
        File file = new File("/Users/mac/IdeaProjects/helloIDEA1");
        removeFile(file);
    }

    /**
     * 遍历删除文件夹
     * @param file
     */
    public void removeFile(File file){
        if(!file.exists()) {
            return;
        }

        //空文件夹直接删除
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(null == files){
                System.out.println("removeDirectory: " + file.getName() + " result: " + file.delete());
            }
            //文件夹不为空
            for (File f : files) {
                //子file为文件夹进一步递归判断
                if(f.isDirectory()){
                    removeFile(f);
                }
                //文件直接删
                if(f.isFile()){
                    System.out.println("removeFile: " + f.getName() + " result: " + f.delete());
                }
            }
            //文件删完后删除文件夹
            System.out.println("removeDirectory: " + file.getName() + " result: " + file.delete());
        }

        if(file.isFile()){
            System.out.println("removeFile: " + file.getName() + " result: " + file.delete());
        }

    }


}
