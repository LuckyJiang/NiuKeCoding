package com.fulong;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

   /* @Autowired
    private JedisPool jedisPool;

    *//**
     * 获取单个对象
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     *//*
    public <T> T get(KeyPrefix keyPrefix ,String key, Class<T> clazz) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + ":" + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str,clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    *//**
     * 设置对象
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     *//*
    public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(value == null || str.length() < 0) {
                return false;
            }
            String realKey = keyPrefix.getPrefix() + ":" + key;
            int seconds = keyPrefix.expireSeconds();
            if(seconds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    *//**
     * 判断key是否存在
     * @param keyPrefix
     * @param key
     * @return
     *//*
    public Boolean exists(KeyPrefix keyPrefix ,String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }


    *//***
     * 删除单个对象
     * @param keyPrefix
     * @param key
     * @return
     *//*

    public Long del(KeyPrefix keyPrefix ,String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.del(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
    *//**
     * 增加值
     * @param keyPrefix
     * @param key
     * @return
     *//*
    public Long incr(KeyPrefix keyPrefix ,String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    *//**
     * 减少值
     * @param keyPrefix
     * @param key
     * @return
     *//*
    public Long decr(KeyPrefix keyPrefix ,String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    *//**
     * 给list头部（左边）添加值
     * @param keyPrefix
     * @param key
     * @param values
     * @param <T>
     * @return
     *//*
    public <T> Long lLeftPush(KeyPrefix keyPrefix ,String key, String... values) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(values);
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.lpush(realKey, values);
        }finally {
            returnToPool(jedis);
        }
    }

    *//**
     * 从list头部（左边）取值
     * @param keyPrefix
     * @param key
     * @return
     *//*
    public String lLeftPop(KeyPrefix keyPrefix ,String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.lpop(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    *//**
     * 从list尾部（右边）取值
     * @param keyPrefix
     * @param key
     * @return
     *//*
    public String lRightPop(KeyPrefix keyPrefix ,String key) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.rpop(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    *//**
     * 获取list指定范围的值
     * @param keyPrefix
     * @param key
     * @param startNum
     * @param stopNum
     * @return
     *//*
    public List<String> lRange(KeyPrefix keyPrefix , String key, Integer startNum, Integer stopNum) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.lrange(realKey, startNum, stopNum);
        }finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if(clazz == String.class) {
            return (String)value;
        } else if(clazz == long.class || clazz == Long.class) {
            return "" + value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() < 0) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        } else if(clazz == String.class) {
            return (T)str;
        } else if(clazz == long.class || clazz == Long.class) {
            return (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }*/
}
