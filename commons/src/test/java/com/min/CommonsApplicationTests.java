package com.min;

import com.min.entitys.ModelProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonsApplicationTests {
	@Autowired(required = false)
	private ModelProperties modelProperties;

	/*@Autowired
	private A a;*/  //他的实现类不能有多个，否则报错


	@Autowired
	private A b;  //正确

	@Autowired
	private  C c;  //可以调用C和B和接口的方法

	/**
	 * A 是接口，B是A的实现，C继承B，将B和C同时注入到容器中
	 * @Autowired :先根据class找，在根据对象的名称来找
	 * 	private A b;b是B的实现类
	 * 	private A a:报错
	 */
	@Test
	void contextLoads() {
//		b.test();
//		c.test();
//		c.testB();
//		c.test1();
//		System.out.println(modelProperties);
		b.test001();
	}

}
