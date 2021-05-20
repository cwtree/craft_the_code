package com.chiwei.craft.code.delayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年5月20日
 */
public class DelayQueueJDKTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DelayQueue<MyOrder> dq = new DelayQueue<>();
		DateTime now = DateUtil.date();
		Date time1 = DateUtil.offset(now, DateField.SECOND, 10);
		Date time2 = DateUtil.offset(now, DateField.SECOND, 5);
		Date time3 = DateUtil.offset(now, DateField.SECOND, 12);
		Date time4 = DateUtil.offset(now, DateField.SECOND, 8);

		MyOrder order1 = MyOrder.builder().name("1号订单").id("1").timeStamp(time1.getTime()).build();
		MyOrder order2 = MyOrder.builder().name("2号订单").id("2").timeStamp(time2.getTime()).build();
		MyOrder order3 = MyOrder.builder().name("3号订单").id("3").timeStamp(time3.getTime()).build();
		MyOrder order4 = MyOrder.builder().name("4号订单").id("4").timeStamp(time4.getTime()).build();

		dq.put(order1);
		dq.put(order2);
		dq.put(order3);
		dq.put(order4);
		System.out.println("-----------"+dq);
		for (;;) {
			System.out.println(dq.take());
			Thread.sleep(1000L);
		}

	}

}
