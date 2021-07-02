package com.springtest.config;

import com.springtest.entity.Car;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * 自动装配：
 * 1.@Autowired 自动装配（DI） 支持在方法，构造器，参数，属性上标注 a. 标注在set方法上，会自动调用该方法进行赋值  b.标在有参构造方法上，进行赋值，如果组件只有一个有参构造器，可以省略该注解，Spring也能自动装配 c.放在参数位置，效果一样
 * 		1.默认会优先根据类型去寻找容器中对应的组件，application.getBean(xxx.class); 中的 xxx.class 类型去匹配
 * 		2.如有找到多个相同类型的组件，那么会继续使用属性名作为ID去容器中查找 application.getBean(“xxx”); 中的"xxx" bean名称查找
 * 		@Qualifier("xxx") 指定具体的组件的ID，而不会默认使用属性名
 * 		3.注意的是 自动装配 默认一定要将属性赋值好， 不然在装配时会报错
 * 		4. 要让第三点不报错 只需要在@Autowired中指定required=false  默认是为true的  e.g:@Autowired(required=false)
 *		5.如找到多个相同类型的组件，让Spring自动装配的时候，默认使用首选的bean  只需要在那个bean组件上加上@Primary 此注解即可，
 *		然后需要获取其他相同类型bean时，可配合@Qualifier("xxx")注解 来装配
 * 2.@Resource和@Inject [java规范中的注解] 也支持Spring 的自动装配
 * 		1.@Resource 是默认是按照组件的名称进行查找装配的，若是想装配自定的bean 可增加 name=“xxx”， 如 @Resource(name=“xxx”)
 * 			与@Autowired 不同在于 不能指定默认首选装配的bean（即@Primary），不支持非必须装配到bean的情况（即@Autowired（required=false）），
 *		2.@Inject 基本与 @Autowired 一样，但是 不支持非必须装配到bean的情况（即@Autowired（required=false）），
 *
 *
 * 3.自定义组件想要使用Spring容器底层的一些组件，（如 ApplicationContext， BeanFactory等）
 * 		只需要在自定义组件中实现 xxxAware接口，会调用接口规定的方法注入相关组件
 * 		xxxAware的功能 是使用 xxxAwareProcessor 来处理的
 */
//@PropertySource(value = "classpath:/bean.xml")
//@ComponentScan("com.springtest.entity")
//@Configuration


public class MainConfigOfLifeCycle {

	@Primary
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
