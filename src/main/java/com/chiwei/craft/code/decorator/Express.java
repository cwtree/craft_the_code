package com.chiwei.craft.code.decorator;

/**
 * 快递接口
 * @author chiwei
 *
 */
public interface Express {

	/**
	 * 收件
	 */
	public void receive();
	
	/**
	 * 送件
	 */
	public void deliver();
	
}
