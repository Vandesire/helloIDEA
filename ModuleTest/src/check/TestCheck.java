package check;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Method;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/1/10 12:05 上午
 * @description: 简单的测试框架：当主方法执行后，会自动执行被检测的的所有方法（加了注解），
 *               判断方法是否执行有异常，并最后记录到文件中
 */
public class TestCheck {

    public static void main(String[] args) throws Exception{
        //*1.创建计算器对象
        Calculator calculator = new Calculator();
        //2.获取字节码文件对象
        Class cls = calculator.getClass();

        //3.获取所有方法
        Method[] methods = cls.getDeclaredMethods();
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));

        //统计总数
        int count = 0;
        //异常个数
        int exps = 0;
        for (Method method : methods) {
            //4.判断方法是否有check注解

            //5.有则执行
            if(method.isAnnotationPresent(Check.class)){
                count ++;
                try {
                    method.invoke(calculator);
                } catch (Exception e) {
                    //6.异常捕获
                    exps ++;
                    bw.write(method.getName() + "方法出现异常!");
                    bw.newLine();
                    bw.write("异常名称: " + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常原因: " + e.getMessage());
                    bw.newLine();
                    bw.write("------------------");
                    bw.newLine();
                }
            }
        }

        bw.write("本次测试执行结束，共执行：" + count);
        bw.newLine();
        bw.write("出现异常个数：" + exps);

        bw.flush();
        bw.close();

    }

}
