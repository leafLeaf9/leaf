package com.gousade.utils;

import java.beans.PropertyDescriptor;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public class ExcelUtil {
	/**
	 * 得到Workbook对象
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkBook(MultipartFile file) throws IOException{
		//这样写  excel 能兼容03和07
		InputStream is = file.getInputStream();
		Workbook hssfWorkbook = null; 
		try { 
		    hssfWorkbook = new HSSFWorkbook(is); 
		} catch (Exception ex) {
		    is =file.getInputStream();
		    hssfWorkbook = new XSSFWorkbook(is); 
		}
		return hssfWorkbook;
	}
	
	/**
	 * 得到错误信息
	 * @param sb
	 * @param list
	 * @param i
	 * @param obj
	 * @param name  用哪个属性名去表明不和规定的数据
	 * @param msg
	 * @throws Exception
	 */
	public static void getWrongInfo(StringBuilder sb,List list,int i,Object obj,String name,String msg) throws Exception{
		Class clazz=obj.getClass();
		Object str=null;
		//得到属性名数组 
		Field[] fields = clazz.getDeclaredFields();
		 for(Field f : fields){
			 if(f.getName().equals(name)){
				 //用来得到属性的get和set方法
				 PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
				 //得到get方法
				 Method getMethod=pd.getReadMethod();
				 str = getMethod.invoke(obj);
			 }
		 }
		 if(i==0)
				sb.append(msg+str+";");
		 else if(i==(list.size()-1))
				sb.append(str+"</br>");
		 else
				sb.append(str+";");
	}
	/**
	 * 
	 * @param response
	 * @param wb
	 * @param showFileName
	 * @throws IOException
	 */
	public static void downloadExcel(HttpServletResponse response, Workbook wb, String showFileName) throws IOException {
		 // 判断数据
        if(wb == null) {
        	
        }
        // 重置响应对象
        response.reset();
        // 当前日期，用于导出文件名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = showFileName+sdf.format(new Date())+".xlsx";
        // 指定下载的文件名--设置响应头
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(dateStr.getBytes("gb2312"), "ISO8859-1"));
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 写出数据输出流到页面
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            wb.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}

}
