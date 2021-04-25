package com.gousade.java8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 从每个文件的第二行中提取:之后的数字
 */
public class Test {

	public static void main(String[] args) throws IOException {
		String dictionary = "C:\\Users\\Administrator\\Desktop\\B45-_OPT_3751\\";
		for (int i = 0; i <= 3750; i++) {
			String fileName = dictionary + i + ".xyz";
			readLineVarFile(fileName, 2); //读取指定行的内容
		}

	}

	/**
	 * 获取某行的冒号之后的数字
	 */
	static void readLineVarFile(String fileName, int lineNumber) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //使用缓冲区的方法将数据读入到缓冲区中
		String line = reader.readLine(); //定义行数
		int num = 0;
		while (line != null)    //当行数不为空时，输出该行内容及字符数
		{
			if (lineNumber == ++num) {
				int index = line.indexOf(":");
//				System.out.println("第" + lineNumber + "行数字内容为->" + line.substring(index+1).trim());
				System.out.println(line.substring(index + 1).trim());
			}
			line = reader.readLine();
		}
		reader.close();
	}
}
