package reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author 刘璞
 *
 * 框架类：
 *  不能改变改类的任何代码的前提下，可以帮助我们创建任意类的对象，并且执行其中的任意方法
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception {

        Properties properties = loadConfig();
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        excute(className, methodName);
    }

    /**
     * 加载配置文件
     * @return
     * @throws IOException
     */
    public static Properties loadConfig() throws IOException {
        Properties properties = new Properties();
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config.properties");

        properties.load(inputStream);
        return properties;
    }

    /**
     * 执行
     * @param className
     * @param methodName
     * @throws Exception
     */
    @Deprecated
    public static void excute(String className, String methodName) throws Exception{

        Class class1 = Class.forName(className);
        Object object = class1.newInstance();

        //无参
        Method method = class1.getMethod(methodName);
        method.setAccessible(true);
        Object invoke = method.invoke(object);
        System.out.println(invoke);
    }


}
