package com.chiwei.craft.code.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MacOSManager implements OSManager {

	@Override
	public void reboot() throws OMException {
		// TODO Auto-generated method stub
		if(log.isInfoEnabled()) {
			log.info("Mac OS reboot");
		}
	}

	@Override
	public void shutdown() throws OMException {
		// TODO Auto-generated method stub
		if(log.isInfoEnabled()) {
			log.info("Mac OS shutdown");
		}
	}

}
