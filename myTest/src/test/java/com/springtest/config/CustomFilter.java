package com.springtest.config;

import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Set;

public class CustomFilter implements TypeFilter {
	/**
	 *
	 * @param metadataReader 获取当前正在扫描的类的信息
	 * @param metadataReaderFactory 获取其他所有的类的信息 是一个类信息工厂
	 * for other classes (such as superclasses and interfaces)
	 * @return
	 * @throws IOException
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

		//获取当前正在扫描的类注解的信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		//获取当前正在扫描的类的信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		//获取当前正在扫描的类的资源信息，如路径等
		Resource resource = metadataReader.getResource();
		Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
		for (String name: annotationTypes) {
			System.out.println("获取当前正在扫描的类注解的信息==>"+name);
		}

		//System.out.println("获取当前正在扫描的类的信息==>"+classMetadata.getClassName());
		//System.out.println("获取当前正在扫描的类的资源信息==>"+resource.getFilename());
		//类名包含er的  返回true
		if (classMetadata.getClassName().contains("er")){
			return true;
		}

		return false;
	}
}
