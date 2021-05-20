package com.chiwei.craft.code.delayqueue;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年5月20日
 */
@Getter
@Setter
@ToString
@Builder
public class MyOrder implements Delayed,Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -5452276101691766252L;
	/**
	 * 订单名称
	 */
	private String name;
	/**
	 * 订单id
	 */
	private String id;
	/**
	 * 失效时间，毫秒级
	 */
	private long timeStamp;

	/**
	 * this 比 入参大 返回 1
	 * 相等 返回0
	 * 小 返回 -1
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return unit.convert(timeStamp - System.currentTimeMillis(), unit);
	}

}
