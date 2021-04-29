package com.chiwei.craft.code.test;

public interface ProcessorManager {

	void start() throws PMException;
	
	void stop() throws PMException;
	
	void restart() throws PMException;
	
}
