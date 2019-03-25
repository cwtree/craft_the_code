package com.chiwei.craft.code.command;

/**
 * 具体命令实现，持有具体的接收者对象，完成具体命令
 * @author chiwei
 */
public class ProcessorLookCommand implements Command {

	private LinuxReceiver linux;
	
	public ProcessorLookCommand(LinuxReceiver linux) {
		this.linux = linux;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		linux.lookProcessor();
	}

}
