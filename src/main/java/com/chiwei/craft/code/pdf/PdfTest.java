package com.chiwei.craft.code.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import cn.hutool.core.io.FileUtil;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年9月29日
 */
public class PdfTest {

	private static byte[] pdfGen() {
		ByteArrayOutputStream outputStream = null;
		try {
			Document document = new Document();
			outputStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			document.close();
		} catch (Exception e) {
			System.out.println("------生成pdf失败-------");
		}
		return outputStream.toByteArray();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Document doc = new Document();
		PdfWriter.getInstance(doc, outputStream);
		//PdfWriter.getInstance(doc, new FileOutputStream("D://hello.pdf"));
		doc.open();
		//BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		//Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
		File pic = new File("E://PIC//logo.png");
		Image jpg = Image.getInstance(FileUtil.readBytes(pic));
		jpg.setAlignment(Image.ALIGN_CENTER);
		doc.add(jpg);
		doc.add(jpg);
		doc.add(jpg);
		doc.add(jpg);
		doc.close();
		FileUtil.writeBytes(outputStream.toByteArray(), new File("d://hello.pdf"));
	}

}
