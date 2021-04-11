package com.springtest.config;

import com.springtest.condition.ImportBeanDefinitionRegistrarImpl;
import com.springtest.condition.ImportSelectorImpl;
import com.springtest.condition.LinuxCondition;
import com.springtest.condition.WindowsCondition;
import com.springtest.entity.Color;
import com.springtest.entity.ColorFactoryBean;
import com.springtest.entity.Person;
import com.springtest.entity.Red;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
//满足当前条件，这个类中的配置的所有bean注册才能生效 作用整个类

@Conditional({WindowsCondition.class})
@Configuration
//@Import快速导入组件 id默认是组件的全类名
@Import({Color.class, Red.class, ImportSelectorImpl.class, ImportBeanDefinitionRegistrarImpl.class})
@ComponentScan(value = "com.springtest", excludeFilters = {
				@ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomFilter.class)
		})
public class MainConfig2 {

	/**
	* 默认方法名作为在容器中的id
	* 以下2个属性 在ConfigurableBeanFactory 此类中
	* PROTOTYPE  多实例  prototype  多实例，IOC容器启动不会调用方法创建对象，而是在每次获取时调用方法创建对象
	* SINGLETON  单实例 singleton  单例模式（默认值），IOC 容器启动会调用方法创建对象放入IOC容器中，
	* 								以后每次获取都是直接从容器（类似 map.get（））中拿。
	* REQUEST  request 在web中  同一次请求创建一个实例   很少用到
	* SESSION  session 在web中  同一个session创建一个实例  很少用到
	 *
	 * 单例模式  Lazy懒加载 ： 在IOC启动时不创建对象，在第一次使用时创建对象，并初始化，放入IOC容器中
	*/
	//@Scope("prototype")//用来调整作用域的
	@Lazy
	@Bean("person")//指定bean在容器中的id
	public Person person(){
		System.out.println("创建了实例...");
		return new Person("张三", 26);
	}

	/**
	 * @Conditional({Conditional}) 按照一定的条件进行判断，满足条件给容器中注册bean
	 *
	 * 如果是Windows系统 给容器中注册bill
	 * 如果是linux系统 给容器中注册linus
	 */

	@Bean("bill")
	public Person person01(){
		return new Person("Bill Getes",60);
	}
	@Conditional({LinuxCondition.class})
	@Bean("linus")
	public Person person02(){
		return new Person("Linus",48);
	}

	/**
	 * 给容器中注册组件的几种方式
	 * 1.包扫描 + 组件标注注解 的方式 (@Controller,@Service,@Repository,@Compoent) 这些一般是自己写的
	 * 2.@Bean【一般导入第三方包的组件】
	 * 3.@Import【快速给容器中导入一个组件】
	 * 	 a.@Import(要导入容器中的组件) 容器中就会自动注册这个组件，id默认就是全类名
	 * 	 b.ImportSelector 是一个接口，需要自己实现， 返回的是一个全类名数组，里面含有一个默认的过滤方法，然后传入@Import
	 * 	 c.ImportBeanDefinitionRegistrar
	 * 4.使用Spring提供的FactoryBean(工厂Bean)
	 * 	 a.默认获取到是的工厂Bean调用getObject创建的对象，
	 * 	 b.要获取工厂Bean本身我们需要给ID前面加一个&标识即可获取
	 */
	@Bean
	public ColorFactoryBean colorFactoryBean(){
		return new ColorFactoryBean();
	}
}
