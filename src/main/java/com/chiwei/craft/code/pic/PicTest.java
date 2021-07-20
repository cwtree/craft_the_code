package com.chiwei.craft.code.pic;

import java.io.File;

import org.apache.commons.codec.binary.Base64;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年5月31日
 */
public class PicTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] bb = FileUtil.readBytes("E:"+File.separator+"PIC"+File.separator+"u=1025905372,135143860&fm=26&gp=0.jpg");
		System.out.println("------"+bb.length);
		
		System.out.println(Base64.encodeBase64String(bb));
	}

}

