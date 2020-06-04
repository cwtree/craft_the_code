package com.chiwei.craft.code.guava.bf;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total = 1000000;// 集合大小
		double fpp = 0.00001;
		BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total,fpp);
		for (int i = 0; i < total; i++) {
			bf.put("" + i);
		}
		int counter = 0;
		for (int i = 0; i < total + 10000; i++) {
			if (bf.mightContain(i + "")) {
				counter++;
			}
		}
		System.out.println("匹配元素个数:" + counter);
	}

}
