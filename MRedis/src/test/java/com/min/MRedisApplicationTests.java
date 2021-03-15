package com.min;

import com.min.entity.People;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MRedisApplicationTests {

	@Test
	void contextLoads() {

		People p = new People();
		System.out.println(p.isSex());


		Set<String> set = new HashSet<>();
		set.add("123");
		set.add("2");
		set.add("3");
		set.add("4");
		set.add("5");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("4")){
                iterator.remove();
            }
        }
        System.out.println(set.contains("123"));
		System.out.println(set.contains("234"));
		System.out.println("==============");

		List<String> list = new ArrayList<>();
		list.add("123");
		list.add("123");
		list.add("123");
		list.add("123");
		list.add("456");
		boolean a = list.contains("123");
		boolean b = list.contains("456");
		boolean c = list.contains("23434");
		int size = list.size();
	}

}
