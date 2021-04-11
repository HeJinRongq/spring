package com.springtest.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {

	public Cat(){
		System.out.println("Cat Constructor");
	}

	@Override
	public void destroy(){
		System.out.println("Cat destory");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Cat init");
	}
}
