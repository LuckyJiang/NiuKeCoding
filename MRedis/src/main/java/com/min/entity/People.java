package com.min.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/11/27 16:26
 */
@Getter
@Setter
@ToString
public class People implements Serializable {

    private String id;

    private String name;

    private int age;

    private boolean sex;

    private List<String> habbit = new ArrayList<>();

    private Address address;

    public People() {

    }

    public void init() {
        this.id = UUID.randomUUID().toString();
        this.name = "我的名字" + this.id.substring(0,3);
        this.address = new Address();
        this.address.province = "我的省是：" + this.id.substring(0,4);
        this.habbit.addAll(Arrays.asList("唱歌","跳舞"));
    }

    /**
     * Address 是People的内部类，必须是static的，否则在序列化的时候会报错
     */

    @Getter
    @Setter
    @ToString
    public static class Address implements Serializable {
        private String province;

        public Address(){

        }

    }


}
