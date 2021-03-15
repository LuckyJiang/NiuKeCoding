package com.min.distributedlock;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/11/25 17:24
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 实现分布式锁的步骤
 * 第一步：通过redis的setnx方式（不存在则设置）,往redis上设置一个带有过期时间的key，如果设置成功，则获得了分布式锁。
 *         这里设置过期时间，是防止在释放锁的时候出现异常导致锁释放不掉。
 * 第二步：执行完业务操作之后，删除该锁。
 *
 * 实现分布式锁的重点：（两个原子操作）
 *      1、加锁和设置过期时间必须是原子的
 *      2、value值的对比和删除锁操作必须是原子的
 */
@Component
public class DistributedLock {

    @Autowired
    private RedisTemplate redisTemplate;

    private String lunaScript = "unlock.luna";


    /**
     * 不推荐
     * distributedLock1的遗留问题：
     *      加分布式锁，可能会出现的问题是发生异常，没有释放锁就会造成死锁，解决方案：distributedLock2
     */
    public void distributedLock1(){
        /**
         * setIfAbsent《===》setnx
         * 返回false为加锁失败，返回true加锁成功
         */
        //加分布式锁，可能会出现的问题是发生异常，没有释放锁就会造成死锁，解决方案：distributedLock2
        Boolean addlock = redisTemplate.opsForValue().setIfAbsent("addlock", "111");
        if(addlock){
            System.out.println("加锁成功，并释放锁");
            redisTemplate.delete("addlock"); //删除key就是释放锁
        }else{
            System.out.println("加锁失败");
        }
        distributedLock1();
    }

    /**
     * 解决distributedLock1发生死锁的情况：使得加锁和设置过期时间为原子操作即可
     * 删锁时的遗留问题：
     *      获取锁之后，开始执行业务代码，如果业务代码时间特别长，导致加的锁过期了，这时候其他人会重新获取到锁；
     *      当我们在把业务代码执行完去删锁的时候，就删掉了别人的锁，
     * 解决方案：distributedLock3
     */
    public void distributedLock2(){
        //执行原子操作，加锁和设置过期时间，即使发生异常，时间到之后，锁自动失效
        Boolean addlock = redisTemplate.opsForValue().setIfAbsent("addlock", "111", 300, TimeUnit.SECONDS);
        if(addlock){
            System.out.println("加锁成功，并释放锁");
            redisTemplate.delete("addlock"); //删除key就是释放锁,释放锁和时间过期双重保障
        }else{
            System.out.println("加锁失败");
        }
        distributedLock2();
    }


    /**
     * distributedLock3，VALUE 设置为自己的ID,在解锁的时候判断,当前锁的值是否与自己的值相等，但是该方法仍然是不安全的
     * 删锁时的遗留问题：
     *     网络延迟原因，概率很小：redis返回key对应的value过程中，锁过期了，被其他线程占用，当删除锁时，删除的仍然是其他线程的锁
     *  解决方案：distributedLock4，value对比和删除锁一定要是原子操作
     */
    public void distributedLock3(){
        String token = UUID.randomUUID().toString();
        Boolean addlock = redisTemplate.opsForValue().setIfAbsent("addlock", token, 300, TimeUnit.SECONDS);
        if(addlock){
            System.out.println("加锁成功，并释放锁");
            String lockValue = (String) redisTemplate.opsForValue().get("addlock");
            //删锁之前判断锁是否过期，如果加锁前后的值相等，则没有过期，可以删除
            if(lockValue.equals(token)){
                redisTemplate.delete("addlock");
            }
        }else{
            System.out.println("加锁失败");
            distributedLock3();
        }
    }

    /**
     * distributedLock4: 通过luna脚本实现：value对比和删除锁是原子操作
     */
    public void distributedLock4(){
        //执行脚本1
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        String token = UUID.randomUUID().toString();
        Boolean addlock = redisTemplate.opsForValue().setIfAbsent("addlock", token, 300, TimeUnit.SECONDS);
        if(addlock){
            try{
                System.out.println("加锁成功，并释放锁");
            }finally {
                //调用脚本进行解锁
                //泛型指的是返回值的类型
                DefaultRedisScript<Integer> defaultRedisScript = new DefaultRedisScript<Integer>(script);
                Long addLock = (Long) redisTemplate.execute(defaultRedisScript, Arrays.asList("addLock"), token);
            }
        }else{
            System.out.println("加锁失败");
            distributedLock4();
        }
    }


    public void getCatalogJsonFromDbRedisLock() {
        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock",uuid,300,TimeUnit.SECONDS);
        if(lock){
            try{
                System.out.println("业务逻辑");
            }finally{
                try {
                    Boolean rs1 = lock("lock", uuid,lunaScript);
                    redisTemplate.delete("lock");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("分布式解锁成功");
            }
        }else{
            System.out.println("分布式锁获取失败");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getCatalogJsonFromDbRedisLock();
        }
    }

    public Boolean lock(String key, String value,String luaPath) throws IOException {
        DefaultRedisScript<Boolean> lockScript = new DefaultRedisScript<>();
        ClassPathResource resource = new ClassPathResource(luaPath);
        ResourceScriptSource source = new ResourceScriptSource(resource);
        lockScript.setScriptSource(source);
        lockScript.setResultType(Boolean.class);
        Boolean result = (Boolean) redisTemplate.execute(lockScript, Arrays.asList(key, value));
        return result;
    }

}
