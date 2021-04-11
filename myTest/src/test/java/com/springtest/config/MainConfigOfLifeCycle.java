package com.springtest.config;

import com.springtest.entity.Car;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * bean的生命周期
 *  bean创建  初始化  销毁的过程
 *  bean容器管理bean的生命周期、
 *  可自定义初始化与销毁方法，容器在bean进行到当前生命周期的时候来调用我们的自定义的初始化与销毁方法
 *  1.指定初始化和销毁方法
 *  	单实例:init-method=""  destroy-method="" 这是xml 配置文件中的配置方式
 *  	单实例：注解的方式为 @Bean(initMethod = "",destroyMethod = "")
 *  	多实例：是在获取时才创建对象，调用初始化方法，在销毁时要自己手动调用销毁方法。
 *  2.通过实现InitializingBean接口，调用初始化方法 实现初始化逻辑  实现DisposableBean接口 调用销毁逻辑方法
 *  3.通过JSR250标准，
 *  	@PostConstruct 调用初始化方法
 * 		@PreDestroy 在bean在销毁前调用的方法 通知我们做清理工作
 *  4.BeanPostProcessor【interface】  bean的后置处理器
 *  	在bean的初始化前后进行一些处理
 *  	postProcessBeforeInitialization：在bean的任何初始化方法调用之前工作
 *  	postProcessAfterInitialization：在bean的任何初始化方法调用之后工作
 *
 *  BeanPostProcessor 的执行过程及原理
 *
 *	遍历得到所有的	BeanPostProcessor，每个进行执行postProcessBeforeInitialization方法，
 *	但是一旦返回null  跳出for循环  就不会被执行postProcessBeforeInitialization
 *
 * 	populateBean(beanName, mbd, instancewrapper)  <== 这是给bean进行属性赋值的
 *	这之后执行以下方法
 *
 *	InitializeBean
 *	{
 *		applyBeanPostProcessorBeforeInitialization(wrappedBean, beanName) 初始化之前
 *  	invokeInitMethods(beanName, wrappedBean, mbd) 执行初始化方法
 *  	applyBeanPostProcessAfterInitialization(wrappedBean, beanName) 初始化之后
 *	}
 *
 *	Spring 底层对BeanPostProcessor的使用
 *		bean的赋值，注入其他组件， @Autowired， 生命周期注解功能  @Async，xxx
 *
 * @Value 注解的使用
 * 	主要是给bean属性赋值
 * 	1.直接在属性上标注 传入具体的数值
 * 		如  @Value("zhangsan")
 *  2.可用Spring中的表达式 #{20-2} 如 @Value("#{20-2}")
 *  3.可用来获取属性文件中配置的值  前提是需要导入属性文件 一种方式是在配置文件中导入  一种是在配置类上加@PropertySource(配置文件路径)
 *    然后注值时使用@Value("${xxx}")
 *
 */
@PropertySource(value = "classpath:/bean.xml")
@ComponentScan("com.springtest.entity")
@Configuration
public class MainConfigOfLifeCycle {

	//设置初始化方法与销毁方法
	@Bean(initMethod = "init",destroyMethod = "destroy")
	public Car car(){
		return new Car();
	}

	//InitializingBean
	//DisposableBean
	//@PostConstruct
	//@PreDestroy
	//@Resource

}
