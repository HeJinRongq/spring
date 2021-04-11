package com.springtest;

import com.springtest.config.MainConfig;
import com.springtest.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testMain {
	public static void main(String[] args) {
		//根据配置文件获取bean
		//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		//String displayName = applicationContext.getDisplayName();
		//获取具体配置的实例
		//Person person = (Person) applicationContext.getBean("person");
		//根据配置类获取容器
		ApplicationContext applicationContext  = new AnnotationConfigApplicationContext(MainConfig.class);
		Person person = applicationContext.getBean(Person.class);
		System.out.println(person);
		//获取实例在容器中的名字
		String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
		//遍历输出
		for (String beanName : beanNamesForType) {
			System.out.println(beanName);
		}
		System.out.println(beanNamesForType);

	}
}
