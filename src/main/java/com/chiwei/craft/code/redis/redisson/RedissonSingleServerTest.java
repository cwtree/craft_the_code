package com.chiwei.craft.code.redis.redisson;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RBucket;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

public class RedissonSingleServerTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Config config = new Config();
		// 172.28.72.104 8380 cmcc2019
		// redis://:cmcc2019@172.28.72.104:8380/0
		config.useSingleServer().setAddress("redis://172.28.72.104:8380").setPassword("cmcc2019").setDatabase(0)
				.setConnectionPoolSize(4).setConnectionMinimumIdleSize(2);
		RedissonClient rc = Redisson.create(config);
		// String KV操作
		RBucket<String> cache = rc.getBucket("mykey");
		cache.set("myvalue", 10, TimeUnit.SECONDS);
		System.out.println("缓存数据：" + cache.get());
		// 列表操作
		RDeque<String> list = rc.getDeque("mylist");
		list.addFirst("list value");// lpush
		System.out.println("LIST数据：" + list.pollLast());// rpop
		// 阻塞取队列数据
		RBlockingDeque<String> bList = rc.getBlockingDeque("myBList", new StringCodec());
		// bList.addFirst("list value");
		System.out.println("BLIST数据：" + bList.takeLast());// rpop
		// 其它API操作参照Redisson Github对照表
		// https://github.com/redisson/redisson/wiki/11.-Redis%E5%91%BD%E4%BB%A4%E5%92%8CRedisson%E5%AF%B9%E8%B1%A1%E5%8C%B9%E9%85%8D%E5%88%97%E8%A1%A8

	}

}
