package com.chiwei.craft.code.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NginxProcessorManager implements ProcessorManager {

	@Override
	public void start() throws PMException {
		// TODO Auto-generated method stub
		if(log.isInfoEnabled()) {
			log.info("NGINX processor start");
		}
	}

	@Override
	public void stop() throws PMException {
		// TODO Auto-generated method stub
		if(log.isInfoEnabled()) {
			log.info("NGINX processor stop");
		}
	}

	@Override
	public void restart() throws PMException {
		// TODO Auto-generated method stub
		if(log.isInfoEnabled()) {
			log.info("NGINX processor restart");
		}
	}

}
