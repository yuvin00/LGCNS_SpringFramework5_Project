package myspring.di.xml.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import myspring.di.xml.Hello;
import myspring.di.xml.Printer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloConfig.class)
public class HelloConfigTest {
	@Autowired
	Hello hello;
	
	@Autowired
	Printer stringPrinter;
	
	@Resource(name = "helloCons")
	Hello hello2;
	
	@Test
	void helloCons() {
		assertEquals("Hello 생성자", hello2.sayHello());
		hello2.print();
	}
	
	
	@Test @Disabled
	void hello() {
		assertEquals("Hello 스프링", hello.sayHello());
		hello.print();
		assertEquals("Hello 스프링", stringPrinter.toString());
		
	}
}