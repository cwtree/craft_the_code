package com.chiwei.craft.code.redis.redisson;


import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonSentinelTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Config config = new Config();
		// 172.28.72.104 8380 cmcc2019
		// redis://:cmcc2019@172.28.72.104:8380/0
		config.useSingleServer().setAddress("redis://172.21.44.25:8379").setPassword("cmcc2019").setDatabase(0)
				.setConnectionPoolSize(4).setConnectionMinimumIdleSize(2);
		RedissonClient rc = Redisson.create(config);

		// useSingleServer().setAddress("redis://172.28.72.104:8380").setPassword("cmcc2019").setDatabase(10)
		//具体rc各种API操作参见RedissonSingleServerTest类
		RAtomicLong rl = rc.getAtomicLong("hostname-baidu");
		Long res = rl.addAndGet(12);
		System.out.println("=================="+res);
	}

}
