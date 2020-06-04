package com.chiwei.craft.code.spring;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClassA {

	@Resource
	private ClassB b;
	
	public ClassA() {
		log.info("ClassA Constructed ...");
	}
	
}
