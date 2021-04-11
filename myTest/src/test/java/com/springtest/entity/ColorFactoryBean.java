package com.springtest.entity;

import org.springframework.beans.factory.FactoryBean;

//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {
	/**
	 * 返回一个Color对象，这个对象会添加到容器中
	 * @return
	 * @throws Exception
	 */
	@Override
	public Color getObject() throws Exception {
		System.out.println("ColorFactoryBean==>getObject");
		return new Color();
	}

	@Override
	public Class<?> getObjectType() {
		return Color.class;
	}

	/**
	 * 控制创建的对象是否是单实例
	 * 如果是单例 返回true 这个bean在容器中只保留一份
	 * 如果是false 多实例，每次获取都能重新创建一个
	 * @return
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
}
