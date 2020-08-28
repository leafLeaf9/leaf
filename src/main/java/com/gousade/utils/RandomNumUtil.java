package com.gousade.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomNumUtil {
	public static String generateId(){
		Date date=new Date();
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String id=dateformat.format(date);
		for(int i=0;i<4;i++){
			int num=(int)(Math.random()*10);
			id=id+num;
		}
		return id;
	}
}
