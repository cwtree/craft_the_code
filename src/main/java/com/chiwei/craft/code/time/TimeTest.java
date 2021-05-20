package com.chiwei.craft.code.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate now = LocalDate.now();
		System.out.println(now);
		LocalDateTime now2 = LocalDateTime.now();
		System.out.println(now2);
		LocalTime now3 = LocalTime.now();
		System.out.println(now3);
		int h = 1;
		System.out.println(h >>> 16);
	}

}
