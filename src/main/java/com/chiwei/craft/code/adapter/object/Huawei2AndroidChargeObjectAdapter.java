package com.chiwei.craft.code.adapter.object;

import com.chiwei.craft.code.adapter.clazz.AndroidCharge;
import com.chiwei.craft.code.adapter.clazz.HuaweiCharge;

public class Huawei2AndroidChargeObjectAdapter implements AndroidCharge {

	private HuaweiCharge huawei;

	public Huawei2AndroidChargeObjectAdapter(HuaweiCharge huawei) {
		super();
		this.huawei = huawei;
	}

	@Override
	public void androidCharge() {
		// TODO Auto-generated method stub
		System.out.println("对象模式适配器实现方式");
		huawei.huaweiCharge();
	}

}
