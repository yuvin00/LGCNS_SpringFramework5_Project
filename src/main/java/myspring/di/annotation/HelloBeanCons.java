package myspring.di.annotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * 전략2 ( 어노테이션 + XML 같이 사용하는 방식 )
 * Constructor Injection 
 * - 인자있는 생성사를 통해서 한꺼번에 주입 받는 방식
 */

@Component
public class HelloBeanCons {
	String name;
	
	PrinterBean printer;
	
	List<String> names;

	@Autowired
	public HelloBeanCons(
			//어노테이션생성자
			@Value("${app.constructor.name}") String name, 
			@Qualifier("consolePrinter") PrinterBean printer) {
		System.out.println(this.getClass().getName() + " 인자가 있는 생성자 호출됨!");
		this.name = name;
		this.printer = printer;
	}

	public List<String> getNames() {
		return this.names;
	}

	@Value("#{'${app.namelist}'.split(',')}")
	public void setNames(List<String> list) {
		this.names = list;
	}

	public String sayHello() {
		return "Hello " + name;
	}

	public void print() {
		this.printer.print(sayHello());
	}

}