package myspring.di.annotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloBeanCons {
	String name;
	
	PrinterBean printer;
	
	List<String> names;

	@Autowired
	public HelloBeanCons(
			@Value("어노테이션생성자") String name, 
			@Qualifier("stringPrinter") PrinterBean printer) {
		System.out.println(this.getClass().getName() + " 인자가 있는 생성자 호출됨!");
		this.name = name;
		this.printer = printer;
	}

	public List<String> getNames() {
		return this.names;
	}

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