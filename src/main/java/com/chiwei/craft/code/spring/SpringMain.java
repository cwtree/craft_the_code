package com.chiwei.craft.code.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		//AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		//acac.refresh();
		System.out.println(acac.getBean(ClassB.class));
		log.info("...");
	}

}
