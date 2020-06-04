package com.chiwei.craft.code.tomcat;

import org.apache.catalina.startup.Tomcat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TomcatServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(9999);
		try {
			tomcat.start();
			log.info("tomcat started ...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tomcat.addWebapp("/boot", "/Users/chiwei/myspace/desighpattern/craft_the_code");
		tomcat.getServer().await();
	}

}
