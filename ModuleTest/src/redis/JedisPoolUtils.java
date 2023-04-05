package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/2/25 1:43 下午
 *
 *  jedisPool工具类
 */
public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        //1.读取配置文件
        InputStream inputStream = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedisPool.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.创建配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));

        //3.初始化连接池对象
        jedisPool = new JedisPool(config, properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")),
                Integer.parseInt(properties.getProperty("timeout")), properties.getProperty("password"));
    }

    /**
     * 获取连接
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
