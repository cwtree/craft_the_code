package com.chiwei.craft.code.spring;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClassB {
	
	@Resource
	private ClassA a;

	public ClassB() {
		log.info("ClassB Constructed ...");
	}
	
}
