package com.chiwei.craft.code.command;

/**
 * 具体命令实现，持有具体的接收者对象，完成具体命令
 * 
 * @author chiwei
 */
public class CreateFileCommand implements Command {

	private WindowsReceiver windows;

	public CreateFileCommand(WindowsReceiver windows) {
		// TODO Auto-generated constructor stub
		this.windows = windows;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		windows.createFile();
	}

}
