package com.chiwei.craft.code.redis.redisson;

import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.redisson.api.StreamMessageId;
import org.redisson.config.Config;

public class RedissonSingleServerTest2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Config config = new Config();
		// 172.28.72.104 8380 cmcc2019
		// redis://:cmcc2019@172.28.72.104:8380/0
		config.useSingleServer().setAddress("redis://172.21.44.25:8379").setPassword("cmcc2019").setDatabase(0)
				.setConnectionPoolSize(4).setConnectionMinimumIdleSize(2);
		RedissonClient rc = Redisson.create(config);
		// String KV操作
		/*RBucket<String> cache = rc.getBucket("mykey");
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
		*/
		RStream<String, String> access = rc.getStream("waf_access_log");
		//access.createGroup("access_group");
		Map<StreamMessageId, Map<String, String>> messages = access.readGroup("access_group", "consumer_1");
		for (Map.Entry<StreamMessageId, Map<String, String>> entry : messages.entrySet()) {
		  Map<String, String> msg = entry.getValue();
		  System.out.println("----------------------"+msg);

		  access.ack("access_group", entry.getKey());
		}
		
	}

}
