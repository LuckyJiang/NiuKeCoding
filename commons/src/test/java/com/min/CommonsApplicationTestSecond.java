package com.min;

import com.min.entitys.ModelProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonsApplicationTestSecond {
	@Autowired(required = false)
	private ModelProperties modelProperties;

	@Autowired
	private A a ;

	@Test
	void contextLoads() {
		a.a();
		a.b();
	}

}
