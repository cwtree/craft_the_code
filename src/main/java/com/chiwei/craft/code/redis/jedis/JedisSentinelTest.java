package com.chiwei.craft.code.redis.jedis;

import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.google.common.collect.Sets;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 哨兵集群配置里的master名字
		String masterName = "mymaster";
		Set<String> sentinels = Sets.newHashSet();
		sentinels.add(new HostAndPort("172.28.72.104", 28379).toString());
		sentinels.add(new HostAndPort("172.28.72.124", 28380).toString());
		sentinels.add(new HostAndPort("172.28.72.124", 28381).toString());
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		// 连接数配置也可放到外部配置文件去
		config.setMaxTotal(128);
		config.setMaxIdle(16);
		config.setMinIdle(4);
		config.setMaxWaitMillis(1000L);
		config.setTestWhileIdle(true);
		config.setTimeBetweenEvictionRunsMillis(30000L);
		config.setMinEvictableIdleTimeMillis(600000L);
		config.setNumTestsPerEvictionRun(-1);
		config.setJmxEnabled(false);
		JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinels, config, 5000, "cmcc2019");
		Jedis jedis = pool.getResource();
		jedis.setex("a", 10, "b");
		System.out.println("---"+jedis.get("a"));
		jedis.close();
	}

}
