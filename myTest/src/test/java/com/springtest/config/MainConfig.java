package com.springtest.config;

import com.springtest.entity.Person;
import com.springtest.service.TestService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
//ComponentScan 单策略扫描包 value:指定要扫描的包 useDefaultFilters:是否采用默认过滤规则
//ComponentScans 多策略扫描包 value：多个ComponentScan
//excludeFilters 排除哪些类不扫描 可多条件  参数是个数组
//includeFilters 扫描类时 只包含哪些类

@ComponentScans(value = {
		/*@ComponentScan(value = "com.springtest", excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
				//@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = TestService.class)
		}, useDefaultFilters = false),*/
		@ComponentScan(value = "com.springtest", excludeFilters = {
				@ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomFilter.class)
		})
})
public class MainConfig {

	//默认方法名作为在容器中的id
	@Bean("person")//指定bean在容器中的id
	public Person person(){
		return new Person("张三", 26);
	}
}
