package com.springtest.condition;


import com.springtest.entity.RainBow;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ImportBeanDefinitionRegistrarImpl implements ImportBeanDefinitionRegistrar {
	/**
	 * AnnotationMetadata: 当前类的注解信息
	 * BeanDefinitionRegistry：BeanDefinition注册类
	 * 通过调用BeanDefinitionRegistry接口的registerBeanDefinition()方法，可以将所有需要添加到容器中的bean注入到容器中。
	 * BeanDefinitionRegistry.registerBeanDefinition()
	 */
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry){
		boolean defRed = registry.containsBeanDefinition("com.springtest.entity.Red");
		boolean defBlue = registry.containsBeanDefinition("com.springtest.entity.Blue");
		//指定bean的定义信息 （bean的类型，bean的作用域）
		BeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
		if (defRed && defBlue){
			registry.registerBeanDefinition("rainBow",beanDefinition);
		}

	}
}
