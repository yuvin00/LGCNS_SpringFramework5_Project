package myspring.di.xml;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class HelloBeanSpringTest {
	@Autowired
	Hello hello;
	
	//@Autowired - Bean의 Class Type으로 의존하는 객체를 찾는 방식
	@Autowired
	@Qualifier("helloCons")
	Hello hello2;
	
	//@Resource - Bean의 name(id) 값으로  의존하는 객체를 찾는 방식
	@Resource(name = "helloCons")
	Hello hello3;
	
	@Test @Disabled
	void helloBean() {
		assertEquals("Hello 스프링", hello.sayHello());
		
		assertEquals("Hello 생성자", hello2.sayHello());
		
		assertEquals("Hello 생성자", hello3.sayHello());
		
		hello2.print();		
	}
	
	@Test
	void list() {
		for(String value:hello2.getNames()) {
			System.out.println(value);
		}
		
		assertEquals(4, hello2.getNames().size());
		assertEquals("스프링프레임워크", hello2.getNames().get(0));
	}
	
}