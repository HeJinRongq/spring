package com.springtest.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.springtest.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author HeJinRong
 */
@Configuration
//扫描包
@ComponentScan("com.springtest")
public class MainConfig {

	//默认方法名作为在容器中的id
	@Bean("person")//指定bean在容器中的id
	public Person person(){
		return new Person("张三", 26);
	}

	@Bean
	public DataSource getDataSource() throws PropertyVetoException {
		// 获取ComboPooledDataSource 对象
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		// 设置 driver，url，pass，user
		dataSource.setDriverClass("com.mysql.jdbc.Driver");  // 设置Driver有异常（已抛出）
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jeesite?serverTimezone=UTC&useSSL=false");
		dataSource.setPassword("123456");
		dataSource.setUser("root");
		// 返回一个DataSource数据源
		return dataSource;
	}

	@Bean("dataSourceTransactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
		return dataSourceTransactionManager;
	}

}
