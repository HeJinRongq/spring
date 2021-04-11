package com.springtest.config;

import com.springtest.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//扫描包
@ComponentScan("com.springtest")
public class MainConfig {

	//默认方法名作为在容器中的id
	@Bean("person")//指定bean在容器中的id
	public Person person(){
		return new Person("张三", 26);
	}
}
