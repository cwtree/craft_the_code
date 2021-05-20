package com.chiwei.craft.code.netty.memory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

public class MemPoolDemo {

	public static void testUnpooled() {
		long begin = System.currentTimeMillis();
		ByteBuf buf = null;
		int counter = 100000000;
		for (int i = 0; i < counter; i++) {
			buf = Unpooled.buffer(10 * 1024);
			buf.release();
		}
		System.out.println("Execute Time: " + (System.currentTimeMillis() - begin));
	}

	public static void testPooled() {
		PooledByteBufAllocator allocator = new PooledByteBufAllocator(false);
		long begin = System.currentTimeMillis();
		ByteBuf buf = null;
		int counter = 100000000;
		for (int i = 0; i < counter; i++) {
			buf = allocator.heapBuffer(10 * 1024);
			buf.release();
		}
		System.out.println("Execute Time: " + (System.currentTimeMillis() - begin));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testPooled();
	}

}
