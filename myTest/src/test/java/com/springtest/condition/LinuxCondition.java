package com.springtest.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


import java.lang.annotation.Annotation;

//判断是否是Linux系统
public class LinuxCondition implements Condition {

	/**
	 * ConditionContext 判断条件能使用的上下文（环境）
	 * AnnotatedTypeMetadata 注释信息
	 * @param context the condition context
	 * @param metadata the metadata of the {@link org.springframework.core.type.AnnotationMetadata class}
	 * or {@link org.springframework.core.type.MethodMetadata method} being checked
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//是否Linux 系统
		//1.获取到IOC使用的bean工厂
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		//2.获取类加载器
		ClassLoader classLoader = context.getClassLoader();
		//3.获取当前运行环境
		Environment environment = context.getEnvironment();
		//4.获取到bean定义的注册类
		BeanDefinitionRegistry registry = context.getRegistry();

		//是否含有对象名为 person 的对象
		//可以判断容器中bean注册情况，也可以给容器中注册bean
		boolean defBean = registry.containsBeanDefinition("person");

		if (defBean){
			return true;
		}

		String property = environment.getProperty("os.name");
		if (property.contains("linux")){
			return true;
		}
		return false;
	}
}
