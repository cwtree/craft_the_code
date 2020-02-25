package com.chiwei.craft.code.redis.redisson;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonSentinelTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Config config = new Config();
		// 172.28.72.104 8380 cmcc2019
		// redis://:cmcc2019@172.28.72.104:8380/0
		String masterName = "mymaster";
		config.useSentinelServers().setMasterName(masterName).setPassword("cmcc2019")
				.addSentinelAddress("redis://172.28.72.104:28379", 
						"redis://172.28.72.124:28380", "redis://172.28.72.124:28381");

		// useSingleServer().setAddress("redis://172.28.72.104:8380").setPassword("cmcc2019").setDatabase(10)
		RedissonClient rc = Redisson.create(config);
		//具体rc各种API操作参见RedissonSingleServerTest类


	}

}
