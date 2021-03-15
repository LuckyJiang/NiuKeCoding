package com.min.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @author jxm
 * @date 2020/11/25 17:21
 * ------------------------------------------------------------------
 * 博客参考：https://blog.csdn.net/solocoder/article/details/84141759
 *           https://blog.csdn.net/lydms/article/details/105224210
 *           封装的一个单独UTil :https://www.jianshu.com/p/8d3c70f33727
 *
 * ------------------------------------------------------------------
 * 1.创建与Redis的连接
 * 2.针对数据的“序列化/反序列化”，提供了多种可选择策略(RedisSerializer)
 * 3、对五种数据类型进行存储，删除，更新
 * 4.分布式锁:  分布式视频高级-159
 * 5.redis缓存
 * 6.主从集群备份
 *
 * -------------------------------------------------------------------
 */
@Configuration
@EnableCaching  //开启缓存
public class RedisConfig {

    String prefix = "DistributedLock:";

    @Resource
    RedisConnectionFactory redisConnectionFactory;

    /**
     * ReidisTemplate 和StringRedisTemplate的 使用方法是一样的
     * StringRedisTemplate是RedisTemplate的子类
     *
     * 关于序列化：
     *   1、JdkSerializationRedisSerializer：
     *      POJO对象的存取场景，使用JDK本身序列化机制，将pojo类通过ObjectInputStream/ObjectOutputStream进行序列化操作，
     *      最终redis-server中将存储字节序列。是目前最常用的序列化策略。
     *   2、StringRedisSerializer：
     *      Key或者value为字符串的场景，根据指定的charset对数据的字节序列编码成string，
     *      是“new String(bytes, charset)”和“string.getBytes(charset)”的直接封装。是最轻量级和高效的策略。
     *   3、JacksonJsonRedisSerializer：
     *      jackson-json工具提供了javabean与json之间的转换能力，可以将pojo实例序列化成json格式存储在redis中，也可以将json格式的数据转换成pojo实例。
     *      因为jackson工具在序列化和反序列化时，需要明确指定Class类型，因此此策略封装起来稍微复杂。【需要jackson-mapper-asl工具支持】
     * @return
     */
    @ConditionalOnMissingBean(name="redisTemplate")
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(){

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        /**
         * note:如果这里设置了序列化的规则，那么实体的定义可以不用实现Serializable，否则反序列化会报错
         */
        //解决：在客户端查看到的key为乱码
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //解决：在客户端查看到的value为十六进制
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        /*redisTemplate.afterPropertiesSet();*/

        return redisTemplate;
    }


}
