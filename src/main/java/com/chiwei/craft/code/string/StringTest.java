package com.chiwei.craft.code.string;

import org.apache.commons.lang3.StringEscapeUtils;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = StringEscapeUtils.escapeHtml4("<script></script>");
		System.out.println("结果："+str);
	}

}
