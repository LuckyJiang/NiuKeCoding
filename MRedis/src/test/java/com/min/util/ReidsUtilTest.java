package com.min.util;

import com.min.entity.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


/**
 * @author jxm
 * @version 1.0
 * @date 2020/12/1 14:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReidsUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void keys() {
        String strkey = redisUtil.strPrefix + "1002";
        String zsetkey = redisUtil.zsetPrefix + "2001";
        String listkey = redisUtil.listPrefix + "3001";
        String mapkey = redisUtil.mapPrefix + "4001";

        People people = new People();
        people.init();
        //对一个string操作
        /*redisUtil.set(strkey, people);
        Object o = redisUtil.get(strkey);*/
        System.out.println();

        //1、List的操作
        //listOpetators(listkey, people);

        //2、HashMap的操作
        //mapOperators(mapkey);

        //3.Zset的排名
        zsetOperators(zsetkey);


    }

    /**
     * //3.Zset的排名
     * @param zsetkey
     */
    private void zsetOperators(String zsetkey) {
        //1.向Zset中一个的插入值
        redisUtil.zsSet(zsetkey, "小天才3", 10);
        redisUtil.zsSet(zsetkey, "小天才5", 17);
        redisUtil.zsSet(zsetkey, "小天才6", 19);
        redisUtil.zsSet(zsetkey, "小天才1", 1);
        People p = new People();
        p.init();
        redisUtil.zsSet(zsetkey, p, 100);
        //2.向Zset中插入一个set集合
        DefaultTypedTuple<Object> d1 = new DefaultTypedTuple<>("小天才4", 15D);
        DefaultTypedTuple<Object> d2 = new DefaultTypedTuple<>("小天才2", 6D);
        Set<ZSetOperations.TypedTuple<Object>> set =  new HashSet<>();
        set.add(d1);
        set.add(d2);
        redisUtil.zsSet(zsetkey, set);
        //3.按顺序打印set集合
        Set<Object> objects = redisUtil.zsGet(zsetkey, 0, -1);
        System.out.println("按顺序打印set集合:");
        objects.stream().forEach(object-> System.out.println(object));
        //4.根据分数获取指定元素 todo
        Object o = redisUtil.zsGet(zsetkey, "小天才4");
        System.out.println("根据分数获取指定元素:" + o.toString());
        //5.获取指定元素的排名
        Long ran = redisUtil.zsGetWithRank(zsetkey, "小天才4");
        System.out.println("获取指定元素(小天才4)的排名：" + ran);
        //6.返回指定分数范围内的成员个数
        Long aLong = redisUtil.zsGetSize(zsetkey, 10, 100);
        System.out.println("返回指定分数范围内的成员个数(5):" + aLong);
        //7.返回指定排名范围内的成员集合
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisUtil.zsGetWidthScore(zsetkey, 1, 3);
        System.out.println("返回指定排名范围内的成员集合:");
        typedTuples.stream().forEach(t->{
            System.out.println(t.getValue().toString() + "对应的分数：" + t.getScore());
        });
        //8.从集合中删除指定元素
        Long zsdel = redisUtil.zsdel(zsetkey, "小天才1");
        System.out.println("指定删除小天才1，遍历是否删除了小天才1:");
        redisUtil.zsGet(zsetkey, 0, -1).stream().forEach(d->{
            System.out.println(d.toString());
        });
        //9.删除指定排名（索引的元素）
        redisUtil.zsdel(zsetkey, 0, 1);
        System.out.println("删除两个元素，并验证：");
        redisUtil.zsGet(zsetkey, 0, -1).stream().forEach(d->{
            System.out.println(d.toString());
        });
        //10.删除指定分数范围内的元素
        redisUtil.zsdel(zsetkey,10, 15);
        redisUtil.zsGet(zsetkey, 0, -1).stream().forEach(d->{
            System.out.println(d.toString());
        });
    }

    /**
     * List的操作
     * @param listkey
     * @param people
     */
    private void listOpetators(String listkey, People people) {
        //List的操作
        redisUtil.lSet(listkey, people);
        //1.获取所有的list并进行遍历
        List<Object> peoples = redisUtil.lGet(listkey, 0, -1);

        Optional.ofNullable(peoples).ifPresent(ps ->{
            ps.forEach(p-> {
                People pp = (People) p;
                System.out.println(pp.toString());
            });
        });
        System.out.println("========================================");
        //2.获取指定索引的对象，并对其进行更新
        People people1 = (People) redisUtil.lGetIndex(listkey, 2);
        people1.setName("我是手工小天才");
        redisUtil.lUpdateIndex(listkey, 2, people1);
        //3.删除list指定下标的对象
        long l = redisUtil.lGetListSize(listkey);
        redisUtil.lRemove(listkey,1);
        System.out.println("删除前的个数：" + l + "   删除后的个数：" + redisUtil.lGetListSize(listkey));
    }

    /**
     * HashMap的操作
     */
    private void mapOperators(String mapkey) {
        //HashMap的操作
        Map<String, People> map = new HashMap<>();
        People mapPepole = new People();
        mapPepole.init();
        People mapPepole1 = new People();
        mapPepole1.init();
        People mapPepole2 = new People();
        mapPepole2.init();
        People mapPepole3 = new People();
        mapPepole3.init();
        map.put(mapPepole.getId(), mapPepole);
        map.put(mapPepole1.getId(), mapPepole1);
        map.put(mapPepole2.getId(), mapPepole1);
        //1.直接存入map对象
        boolean hmset = redisUtil.hmset(mapkey, map);
        System.out.println("map的当前个数：" + redisUtil.hGetMapSize(mapkey) + "   set一个map集合");
        //2.一个一个的key value存入
        boolean hset = redisUtil.hset(mapkey, mapPepole3.getId(), mapPepole3);
        System.out.println("map的当前个数：" + redisUtil.hGetMapSize(mapkey) + "   set一个个的k-v");
        //3.根据key获取map对象
        Map<Object, Object> hmget = redisUtil.hmget(mapkey);
        System.out.println("根据key获取到map集合：");
        Optional.ofNullable(hmget).ifPresent(hm->{
            hm.forEach((k,v)->{
                System.out.println("项的key: " + k.toString() + "   项的value:" +((People)v).toString());
            });
        });
        //4.根据map内部的key获取对象
        People hget = (People) redisUtil.hget(mapkey, mapPepole1.getId());
        System.out.println("根据map项的key获取到的对象：" + hget.toString());
        //5.根据map内部的一个key删除一个对象
        redisUtil.hdel(mapkey, mapPepole.getId());
        System.out.println("map的当前个数：" + redisUtil.hGetMapSize(mapkey) + "   根据指定项的key删除了一个元素");
        //6.根据map内部的多个key删除对象
        redisUtil.hdel(mapkey, mapPepole1.getId(), mapPepole2.getId());
        System.out.println("map的当前个数：" + redisUtil.hGetMapSize(mapkey) + "   根据指定项的key删除了两个元素");
        //7.根据map内部的一个key更新:就是在原来的key上重新set
    }


}