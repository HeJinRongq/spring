package com.springtest;

import com.springtest.config.MainConfig;
import com.springtest.config.MainConfig2;
import com.springtest.config.MainConfigOfLifeCycle;
import com.springtest.entity.Blue;
import com.springtest.entity.Green;
import com.springtest.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Map;

public class JTest {
	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

	@Test
	public void myTestx(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}

	@Test
	public void myTest(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		//默认单例模式
		Object person = applicationContext.getBean("person");
		Object person1 = applicationContext.getBean("person");
		//单例模式 输出为true  多实例 返回false
		System.out.println(person==person1);
	}

	@Test
	public void IOCTest(){
		String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
		Environment environment =  applicationContext.getEnvironment();
		//获取环境变量的值  Windows 10
		String property = environment.getProperty("os.name");
		System.out.println(property);
		//获取对象的名称
		for (String name : beanNamesForType) {
			System.out.println(name);
		}

		Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);

		System.out.println(persons);

	}

	@Test
	public void testImport(){
		printBeans(applicationContext);
		Green bean = applicationContext.getBean(Green.class);
		System.out.println(bean);
		System.out.println("bean");

		//工厂Bean获取的是调用getObject创建的对象
		Object color = applicationContext.getBean("colorFactoryBean");
		System.out.println("bean的类型："+ color.getClass());
		Object colorFactoryBean = applicationContext.getBean("&colorFactoryBean");
		System.out.println("获取工厂类本身===》"+colorFactoryBean);
	}

	private void printBeans(ApplicationContext applicationContext){
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}

	}

	@Test
	public void test_LifeCycle(){
		//创建IOC容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
		System.out.println("容器创建完成....");
		applicationContext.close();
	}

}
