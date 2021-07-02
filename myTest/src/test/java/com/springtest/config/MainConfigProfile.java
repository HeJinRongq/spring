package com.springtest.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * @Profile : 此注解用于根据当前环境，动态切换或激活系列组件的功能。
 * 			可作用域类上，或者方法上。
 * 	指定在哪个环境的情况下才能被注册到容器中，不指定，全注册：
 * 	@Profile(“环境标识”) 如需设置默认注册bean，--》@Profile(“default”)
 * 	    a.加了环境标识的bean，只有在这个环境被激活的时候才注册到容器中
 * 	    b.如果标注在配置类上，只有是指定的环境的时候，整个配置类才里面的所有配置才开始生效
 * 	    c.没有指定的bean，都能自动注册到容器中
 *  启动方式：
 * 		1.使用启动命令的方式：-Dspring.profiles.active=环境标识  spring就会注册对应标识的bean
 * 	    2.使用代码的方式
 * 如：数据源切换（开发环境，测试环境，正式环境）
 * EmbeddedValueResolverAware 值解析器， 实现set，注入值解析器。
 *
 */

@PropertySource("classpath:/dbConfig.properties")
@Configuration
public class MainConfigProfile implements EmbeddedValueResolverAware {

	@Value("${db.user}")
	private String user;

	private StringValueResolver valueResolver;

	private String driverClass;

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.valueResolver = resolver;
		this.driverClass = valueResolver.resolveStringValue("${db.driverClass}");
	}

	@Profile("testDataSource")
	@Bean
	public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(pwd);
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.71.134:3306/wmp");
		return comboPooledDataSource;
	}

	@Profile("devDataSource")
	@Bean
	public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(pwd);
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.71.134:3306/ga_security_cloud_platform_816");
		return comboPooledDataSource;
	}

	@Profile("prodDataSource")
	@Bean
	public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(pwd);
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.71.134:3306/ga_security_cloud_platform");
		return comboPooledDataSource;
	}



}
