package com.chiwei.craft.code.command;

/**
 * 接受者对象，真正执行命令的对象
 * @author chiwei
 *
 */
public class WindowsReceiver {

	public WindowsReceiver() {
		
	}
	
	public void createFile() {
		System.out.println("windows 创建文件");
	}
	
	public void lookProcessor() {
		System.out.println("windows 查看进程");
	}
	
}
