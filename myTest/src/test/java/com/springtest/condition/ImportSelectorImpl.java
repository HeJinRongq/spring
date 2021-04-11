package com.springtest.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

public class ImportSelectorImpl implements ImportSelector {
	//返回值就是导入到容器中的组件的全类名
	//AnnotationMetadata 表示被@Import注解标注的组件的所有注解等信息
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//此处不能返回null  否则会报空指针异常
		return new String[]{"com.springtest.entity.Blue","com.springtest.entity.Green"};
	}

	//在上面返回的String[] 数组中需要导入的包 进行过滤  默认返回null 就是不过滤 JDK1.8 的新的特性
	@Override
	public Predicate<String> getExclusionFilter() {
		return className -> className.contains("Blue");
	}
}
