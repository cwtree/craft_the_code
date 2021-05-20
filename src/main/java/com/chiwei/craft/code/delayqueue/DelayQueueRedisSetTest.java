package com.chiwei.craft.code.delayqueue;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.redisson.config.Config;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年5月20日
 */
public class DelayQueueRedisSetTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DateTime now = DateUtil.date();
		Date time1 = DateUtil.offset(now, DateField.SECOND, 10);
		Date time2 = DateUtil.offset(now, DateField.SECOND, 5);
		Date time3 = DateUtil.offset(now, DateField.SECOND, 12);
		Date time4 = DateUtil.offset(now, DateField.SECOND, 8);

		MyOrder order1 = MyOrder.builder().name("1号订单").id("1").timeStamp(time1.getTime()).build();
		MyOrder order2 = MyOrder.builder().name("2号订单").id("2").timeStamp(time2.getTime()).build();
		MyOrder order3 = MyOrder.builder().name("3号订单").id("3").timeStamp(time3.getTime()).build();
		MyOrder order4 = MyOrder.builder().name("4号订单").id("4").timeStamp(time4.getTime()).build();

		Config config = new Config();
		// 172.28.72.104 8380 cmcc2019
		// redis://:cmcc2019@172.28.72.104:8380/0
		config.useSingleServer().setAddress("redis://172.21.44.85:8379").setPassword("cmcc2019").setDatabase(10)
				.setConnectionPoolSize(4).setConnectionMinimumIdleSize(2);
		RedissonClient rc = Redisson.create(config);
		// zset操作
		RScoredSortedSet<MyOrder> zset = rc.getScoredSortedSet("ORDER_QUEUE");
		zset.add(order1.getTimeStamp(), order1);
		zset.add(order2.getTimeStamp(), order2);
		zset.add(order3.getTimeStamp(), order3);
		zset.add(order4.getTimeStamp(), order4);

		for (;;) {
			/**
			 * @始终取第一条出来判断
			 * 
			 *             MyOrder order = zset.first();
							if (order != null && order.getDelay(TimeUnit.MILLISECONDS) <= 0) {
								System.out.format("%s 订单已过期", order);
								zset.remove(order);
							} else {
								System.out.format("%s 订单未过期", order);
							}
			 * @另一种做法，直接批量取出已经过期的数据进行处理
			 */
			long nowT = System.currentTimeMillis();
			long start = nowT - 10000;
			long end = nowT + 2000;
			System.out.format("================now-%d start-%d end-%d\n", nowT, start, end);
			Collection<ScoredEntry<MyOrder>> sets = zset.entryRange(start, false, end, false);
			Iterator<ScoredEntry<MyOrder>> it = sets.iterator();
			while (it.hasNext()) {
				ScoredEntry<MyOrder> se = it.next();
				long score = se.getScore().longValue();
				MyOrder order = se.getValue();
				System.out.format("分数-%d 订单数据%s", score, order);
				System.out.println("对过期订单进行业务处理并删除队列数据");
				zset.remove(order);
			}
			Thread.sleep(1000L);
		}
	}

}
