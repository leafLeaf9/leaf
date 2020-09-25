package com.gousade.easyExcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.alibaba.excel.EasyExcel;
import com.gousade.excel.EasyExcelDemoData;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-25 10:31:34
 * @description 
 */
public class EasyExcelTest {
	
	@Test
	public void simpleWrite() {
		String fileName = this.getClass().getResource("/").getPath()+"easytest.xlsx";
		EasyExcel.write(fileName, EasyExcelDemoData.class).sheet("sheet0").doWrite(data());
	}
	
	private List<EasyExcelDemoData> data() {
        List<EasyExcelDemoData> list = new ArrayList<EasyExcelDemoData>();
        for (int i = 0; i < 10; i++) {
            EasyExcelDemoData data = new EasyExcelDemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

}
