package com.springtest.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.springtest.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author HeJinRong
 */
@Configuration
//扫描包
@ComponentScan("com.springtest")
@EnableTransactionManagement
public class MainConfig {

	//默认方法名作为在容器中的id
	@Bean("person")//指定bean在容器中的id
	public Person person(){
		return new Person("张三", 26);
	}

	@Bean
	public DataSource dataSource() {
		// 获取ComboPooledDataSource 对象
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// 设置 driver，url，pass，user
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/jeesite?serverTimezone=UTC&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		// 返回一个DataSource数据源
		return dataSource;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}


	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

}
