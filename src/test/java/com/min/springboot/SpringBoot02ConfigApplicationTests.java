package com.min.springboot;

import com.min.springboot.bean.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBoot02ConfigApplicationTests {

	@Autowired
	private Person person;
	/*@Test
	void contextLoads() {
//		System.out.println(person);
	}*/

	@Test
	public void setPerson() {
		//可变类型：加final和不加final 都可以修改某个属性
		Person p = new Person();
		p.setAge(123);
		p.setFullName("lala");
		setPerson(p);
		System.out.println(p.toString());
	}
	public void setPerson(Person person){
		person.setFullName("nihao");

	}

	/**
	 * 传引用之不可变类
	 */
	@Test
	public void setString() {
		String s = "123";
		changeString(s);
		System.out.println(s);

	}

	public void changeString(String s){
		s += "23";
		System.out.println(s);
	}

	@Test
	public void test01(){
		int [][] array = {{}, {}};
		System.out.println(Find(1, array));
	}

	public boolean Find(int target, int [][] array) {
		int rows = array.length;
		int cols = 1;
		int rowIndex = 0;
		boolean exist = false;
		if(rows > 0){
			/*cols = array[0].length;
			if(target < array[0][0] || target > array[rows-1][cols-1])
				return exist;
			out:
			for (int rIndex = 0; rIndex < rows -1; rIndex++) {
				if( (target < array[rIndex][0] ) && target > array[rIndex][cols-1]){
                    continue;
				}
                for (int cIndex = 0; cIndex < cols -1; cIndex++) {
                    if(target == array[rIndex][cIndex]){
                        exist = true;
                        break out;
                    }
                }
			}*/

		}else {
			return exist;
		}

		return exist;
	}
}
