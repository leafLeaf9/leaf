package com.gousade.controller;

import com.alibaba.excel.EasyExcel;
import com.gousade.commonutils.ResponseResult;
import com.gousade.excel.EasyExcelDemoData;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-25 14:12:18
 * @description
 */
@Api(tags = "easyExcel")
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/easyExcel")
public class EasyExcelController {

	@GetMapping("download")
	public void download(HttpServletResponse response) throws IOException {
		// 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
		String fileName = URLEncoder.encode("测试", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
		response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
		EasyExcel.write(response.getOutputStream(), EasyExcelDemoData.class).sheet("模板").doWrite(data());
	}

	@GetMapping("downloadFailedUsingJson")
	public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
		// 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
			String fileName = URLEncoder.encode("测试", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			// 这里需要设置不关闭流
			EasyExcel.write(response.getOutputStream(), EasyExcelDemoData.class).autoCloseStream(Boolean.FALSE).sheet("模板")
					.doWrite(data());
		} catch (Exception e) {
			// 重置response
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(ResponseResult.renderError().message("下载文件失败," + e.getMessage()));
		}
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
