package com.chiwei.craft.code.command;

/**
 * 持有命令对象，设置命令，执行命令
 * 
 * @author chiwei
 *
 */
public class LenovoComputerInvoker {

	private Command command;

	/**
	 * 设置具体命令
	 * 
	 * @param command
	 */
	public void setCmd(Command command) {
		this.command = command;
	}

	/**
	 * 执行命令
	 */
	public void exeCmd() {
		command.execute();
	}

}
