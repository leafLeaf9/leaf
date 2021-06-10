package com.gousade.java8;

import com.gousade.pojo.SliderCaptchaDto;
import com.gousade.pojo.TestInterface;
import com.gousade.utils.BigDecimalCalculator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 从每个文件的第二行中提取:之后的数字
 */
public class Test {

	public static void main(String[] args) {
		System.out.println(EmergencyOrderStatus.valueOf("ARRIVED").getClass());
		System.out.println(EmergencyOrderStatus.ARRIVED.getStatus());
		System.out.println(EmergencyOrderStatus.ARRIVED);
		System.out.println(EmergencyOrderStatus.ARRIVED.getDescription());
		System.out.println(EmergencyOrderStatus.ARRIVED.toString());
		System.out.println("-----------------------");
		Map<String, Object> map = new HashMap<>(10);
		System.out.println(map.size());
		System.out.println(Math.round(BigDecimalCalculator.divide(7, 4, 1)));
		System.out.println(ManagementFactory.getRuntimeMXBean().getName());
		System.out.println(129 & 128);
		System.out.println("--------------");
		String str1 = null;
		String str2 = "assa" + str1;
		System.out.println(str1);
		System.out.println(str2);
		List<String> list = new ArrayList<>();
		list.add(new String("a"));
		List<String> list2 = list.stream().collect(Collectors.toList());
		System.out.println(list == list2);
		System.out.println(list.get(0) == list2.get(0));
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime now2 = now.plusMinutes(5);
		System.out.println(now.isBefore(now2));
		/*Optional<EmergencyOrderStatus> first = Arrays.stream(
				EmergencyOrderStatus.values()).filter(emergencyOrderStatus -> emergencyOrderStatus.getStatus().equals("10"))
				.findFirst();
		System.out.println(first);
		System.out.println(BigDecimalCalculator.multiply(BigDecimalCalculator.divide(4,6,2), 100));
		System.out.println(BigDecimal.valueOf(400).divide(BigDecimal.valueOf(6),2, RoundingMode.HALF_UP));*/
		SliderCaptchaDto sliderCaptchaDto = new SliderCaptchaDto();
		System.out.println(sliderCaptchaDto instanceof TestInterface);
		System.out.println(sliderCaptchaDto instanceof SliderCaptchaDto);
		Map<String, Object> someMap = new HashMap<>();
		someMap.put("jack", "20");
		someMap.put("bill", "35");
		someMap.keySet().stream().forEach(System.out::println);
		someMap.values().stream().forEach(System.out::println);
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
