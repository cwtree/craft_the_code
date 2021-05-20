package com.chiwei.craft.code.adapter.clazz;

/**
 * 现在只有华为的充电器，但是我的手机是三星的，需要一个将华为充电器转成三星手机可用的转换器
 * 适配器就是这个转换器，将华为充电器包装下，适配了三星手机的充电需求
 * 
 * @author chiwei
 *
 */
public class Huawei2AndroidChargeAdapter extends Mate10ProHuaweiCharge implements AndroidCharge {

	@Override
	public void androidCharge() {
		// TODO Auto-generated method stub
		System.out.println("华为转换成安卓充电器，对外接口是安卓，内部还是华为");
		huaweiCharge();
	}

}
