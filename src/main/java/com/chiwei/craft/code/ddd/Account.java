package com.chiwei.craft.code.ddd;

import javax.annotation.Nonnull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年4月30日
 */
@Getter
@Setter(value = lombok.AccessLevel.PRIVATE)
@ToString
@Builder
public class Account {

	@Nonnull
	private Long number;
	@Nonnull
	private String username;

	/**
	 * 业务行为
	 */
	public String biz() {
		return username + " 的账号是 " + number;
	}

}
