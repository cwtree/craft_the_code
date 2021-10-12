package com.chiwei.craft.code.serial_deserial;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerializeDeserializeWrapper<T> {
	private T data;

	public static <T> SerializeDeserializeWrapper<T> builder(T data) {
		SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
		wrapper.setData(data);
		return wrapper;
	}

}
