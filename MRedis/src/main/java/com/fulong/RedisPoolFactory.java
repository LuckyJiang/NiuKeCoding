//package fulong;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Component
//public class RedisPoolFactory {
//
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private int port;
//    @Value("${redis.timeout}")
//    private int timeout;
//    @Value("${redis.password}")
//    private String password;
//    @Value("${redis.poolMaxTotal}")
//    private int poolMaxTotal;
//    @Value("${redis.poolMaxIdle}")
//    private int poolMaxIdle;
//    @Value("${redis.poolMaxWait}")
//    private int poolMaxWait;
//
//    @Bean
//    public JedisPool jedisPoolFactory(){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(this.poolMaxTotal);
//        jedisPoolConfig.setMaxIdle(this.poolMaxIdle);
//        jedisPoolConfig.setMaxWaitMillis(this.poolMaxWait);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig,this.host,this.port,this.timeout*1000);
//        return  jedisPool;
//    }
//
//}
