package com.gousade.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
 
/**
 * 
 * 生成pdf文件工具类
 *
 */
public class GeneratePDFUtil {
	
	/**
	 * @param response
	 * @param filePath  生成文件的路径
	 * @param showFileName 显示的文件名
	 * @throws IOException
	 */
	 protected static void previewpdf(HttpServletResponse response, String filePath, String showFileName) throws IOException  {
			File f = new File(filePath);
			if (!f.isFile() || !f.exists()) {// 文件异常
				throw new FileNotFoundException("文件异常,请检查文件是否正确:" + filePath);
			}
			// 获取文件流
			InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.setCharacterEncoding("UTF-8");
			//response.setContentType("application/octet-stream;charset=UTF-8");
			//response.addHeader("Content-Disposition", "attachment1;filename=" + new String(showFileName.getBytes("gb2312"), "ISO8859-1"));
			response.setContentType("application/pdf;charset=UTF-8");
			// 下载文件名处理
			response.addHeader("Content-Disposition", "inline;filename=" + new String(showFileName.getBytes("gb2312"), "ISO8859-1"));
			response.addHeader("Content-Length", "" + f.length());
			// 文件流处理
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			//删除临时文件
			File file=new File(filePath);
			file.delete();
		}
	
	

	/**
	 * 
	 * @param templatePath   模板路径
	 * @param newPDFPath     新文件路径 
	 * @param map
	 * @throws IOException 
	 */
	public static  void interviewReportPDF(HttpServletResponse response,String templatePath,String newPDFPath,Map<String, String> map,String showFileName) throws IOException {
		PdfReader reader;
		FileOutputStream out;
		ByteArrayOutputStream bos;
		PdfStamper stamper;
		try {
			out = new FileOutputStream(newPDFPath);// 输出流
			reader = new PdfReader(templatePath);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
 
			// 给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
			BaseFont bf = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			
			/*BaseFont bf = BaseFont.createFont(UtilPath.getRootPath() + "fonts/simsun.ttc,0", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);*/
			form.addSubstitutionFont(bf);
 
			//遍历map装入数据
			for (Entry<String, String> entry : map.entrySet()) {
				form.setField(entry.getKey(), entry.getValue());
			}
			stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
			stamper.close();
			Document doc = new Document();
			PdfCopy copy = new PdfCopy(doc, out);
			doc.open();
			PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
			copy.addPage(importPage);
			doc.close();
		} catch (IOException e) {
			
		} catch (DocumentException e) {
			
		}
		//到页面上进行展示
		previewpdf(response,newPDFPath,showFileName);
		
	}
	
	
	/*public static  void test(String templatePath,String newPDFPath,Map<String, String> map) throws IOException {
		PdfReader reader;
		FileOutputStream out;
		ByteArrayOutputStream bos;
		PdfStamper stamper;
		try {
			out = new FileOutputStream(newPDFPath);// 输出流
			reader = new PdfReader(templatePath);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
 
			// 给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
			BaseFont bf = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			
			BaseFont bf = BaseFont.createFont(UtilPath.getRootPath() + "fonts/simsun.ttc,0", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			form.addSubstitutionFont(bf);
 
			//遍历map装入数据
			for (Entry<String, String> entry : map.entrySet()) {
				form.setField(entry.getKey(), entry.getValue());
			}
			stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
			stamper.close();
			Document doc = new Document();
			PdfCopy copy = new PdfCopy(doc, out);
			doc.open();
			PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
			copy.addPage(importPage);
			doc.close();
		} catch (IOException e) {
			
		} catch (DocumentException e) {
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		Map map=new HashMap();
		map.put("realName", "葛志城");
		map.put("mobile","18069849932");
		map.put("orderType", "费用");
		map.put("expectPrice", "1000");
		map.put("name_1", "物品1");
		map.put("name_2", "物品2");
		map.put("num_1", "1");
		map.put("num_2", "2");
		map.put("price_1", "500");
		map.put("price_2", "250");
		map.put("realName_1", "舒元靖");
		map.put("realName_2", "陈文倩");
		map.put("suggestion_1", "同意");
		map.put("suggestion_2", "同意");
		test("D://feeOrderTemplate.pdf","D://newPDF.pdf",map);
	}*/
}
