package com.chiwei.craft.code.leetcode.algo;

public class URLBlank {

	/**
	 * URL空格处理替换，给定了有效长度
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param S
	 * @param length
	 * @return
	 */
	public static String replaceSpaces(String S, int length) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<length;i++) {
			if(S.charAt(i)==' ') {
				sb.append("%20");
			}else {
				sb.append(S.charAt(i));
			}
		}
		return sb.toString();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(replaceSpaces("sdf s dlfj ",11));
	}

}
