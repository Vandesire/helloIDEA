package reflect;

import annotation.AnnoTest;

import java.lang.reflect.Method;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/1/9 11:43 下午
 */
@AnnoTest(className = "com.test.DemoShow", methodName = "show")
public class ReflectTestByAnnotation {

    public static void main(String[] args) throws Exception {

        //1.解析注解
        //1.1获取该类的字节码文件对象
        Class<ReflectTestByAnnotation> testClass = ReflectTestByAnnotation.class;

        //2.获取该类上的注解
        //本质是在内存中生成一个该注解接口的子类实现对象
        AnnoTest annoTest = testClass.getAnnotation(AnnoTest.class);
        String className = annoTest.className();
        String methodName = annoTest.methodName();

        Class cls = Class.forName(className);

        Object obj = cls.newInstance();

        Method method = cls.getMethod(methodName);
        method.setAccessible(true);

        method.invoke(obj);

    }
}
