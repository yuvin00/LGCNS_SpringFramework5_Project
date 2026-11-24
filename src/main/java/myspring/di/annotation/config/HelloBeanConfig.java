package myspring.di.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//<context:component-scan base-package="myspring.di.annotation" />
@ComponentScan(basePackages = {"myspring.di.annotation"})

//<context:property-placeholder location="classpath:values.properties" />
@PropertySource(value = "classpath:values.properties")
public class HelloBeanConfig {

}