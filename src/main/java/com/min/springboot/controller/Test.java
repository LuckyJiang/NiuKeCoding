package com.min.springboot.controller;

import com.min.springboot.entity.Tree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class Test {


    @GetMapping("test01")
    public Tree test01(){
        Tree tree = new Tree();
        //与isHandle类似 不返回该属性
        tree.setIsCategory(true);
        tree.setName("test01");
        //JsonIgnore: category有值；handle；
        // 即使设置了值 给前天的时候不会返回这个属性
        tree.setCategory("categoryTT");
        //与children类似  不赋值就不会返回
        Set<String> items = new HashSet<>();
        items.add("122222222");
        items.add("11111111");
        tree.setItemIds(items);
        return tree;
    }
}
