package com.chiwei.craft.code.guava.ratelimiter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 令牌桶算法实现
 * 
 * @author chiwei
 * @date 2020年2月16日
 */
public class RateLimiterTest {

	static RateLimiter rateLimiter = RateLimiter.create(2);

	public static void service() {
		boolean flag = rateLimiter.tryAcquire();
		if (flag) {
			System.out.println("##" + Thread.currentThread().getName() + " 调用成功");
		} else {
			System.out.println("##" + Thread.currentThread().getName() + " 调用失败");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		service();
		Thread.sleep(5000L);
		// 限流每秒2次请求
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			try {
				//Thread.sleep(500L);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tpe.submit(() -> {
				service();
			});
		}
		System.out.println(System.currentTimeMillis() - begin);
	}

}
