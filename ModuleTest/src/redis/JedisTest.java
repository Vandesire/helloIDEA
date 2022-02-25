package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/2/22 5:14 下午
 */
public class JedisTest {

    /**
     * v1.0 jedis直连
     */
    @Test
    public void jedisTest(){

        //建立连接，获取连接对象
        Jedis jedis = new Jedis("172.16.221.132", 6379);
        try {
            //使用，方法名同命令行
            String result1 = jedis.set("password", "root");
            String password = jedis.get("password");
            System.out.println("result1: " + result1);
            System.out.println("password: " + password);

            String result2 = jedis.setex("string_ex", 10, "123");
            String string_ex = jedis.get("string_ex");
            System.out.println("result2: " + result2);
            System.out.println("string_ex: " + string_ex);
            System.out.println("waiting 10s expireTime effective");
            //等待11秒
            Thread.currentThread().sleep(11000);
            String string_ex_wait = jedis.get("string_ex");
            System.out.println("string_ex_wait: " + string_ex_wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭连接
        jedis.close();

    }


    /**
     * v2.0 jedisPool 连接池
     */
    @Test
    public void jedisPoolTest(){
        //1.创建连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        //2.创建连接池
        JedisPool jedisPool = new JedisPool(config, "172.16.221.132", 6379);

        //3.获取连接
        Jedis jedis = jedisPool.getResource();

        String username = jedis.get("username");

        System.out.println("username: " + username);

        jedis.close();

    }


    /**
     * v3.0 jedisPoolUtils 工具类
     */
    @Test
    public void jedisPoolUtilsTest(){
        Jedis jedis = JedisPoolUtils.getJedis();
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            String type = jedis.type(key);
            System.out.println("type: " + type + ", key: " + key);
        }
        jedis.close();
    }

}
