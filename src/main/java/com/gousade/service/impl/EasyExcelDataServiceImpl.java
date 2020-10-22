package com.gousade.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gousade.commonutils.ResponseResult;
import com.gousade.entity.EasyExcelData;
import com.gousade.entity.listener.EasyExcelDataListener;
import com.gousade.mapper.EasyExcelDataMapper;
import com.gousade.service.EasyExcelDataService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author woxigousade <woxigsd@gmail.com>
 * @since 2020-09-25
 */
@Service
public class EasyExcelDataServiceImpl extends ServiceImpl<EasyExcelDataMapper, EasyExcelData> implements EasyExcelDataService {

	@Override
	public ResponseResult upload(MultipartFile file, EasyExcelDataService easyExcelDataService) throws IOException {
		EasyExcel.read(file.getInputStream(), EasyExcelData.class, new EasyExcelDataListener(easyExcelDataService)).sheet().doRead();
		return ResponseResult.renderSuccess().message("上传成功");
	}

	@Override
	public void download(HttpServletResponse response, EasyExcelDataService easyExcelDataService) throws IOException {
		try {
			List<EasyExcelData> list = easyExcelDataService.list(
					new QueryWrapper<EasyExcelData>().lambda().isNotNull(EasyExcelData::getCreateTime));
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			String fileName = URLEncoder.encode("测试download", "UTF-8").replaceAll("\\+", "%20");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			EasyExcel.write(response.getOutputStream(), EasyExcelData.class).autoCloseStream(Boolean.FALSE).sheet("sheet1")
					.doWrite(list);
		} catch (Exception e) {
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(ResponseResult.renderError().message("下载文件失败," + e.getMessage()));
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void downloadNoFailure(HttpServletResponse response, EasyExcelDataService easyExcelDataService) throws IOException {
		List<EasyExcelData> list = easyExcelDataService.list(
				new QueryWrapper<EasyExcelData>().lambda().isNotNull(EasyExcelData::getCreateTime));
		List<EasyExcelData> list2 = easyExcelDataService.list(new QueryWrapper<EasyExcelData>().isNotNull("date"));
		// 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
		String fileName = URLEncoder.encode("测试downloadNoFailure", "UTF-8").replaceAll("\\+", "%20");
		response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
		EasyExcel.write(response.getOutputStream(), EasyExcelData.class).sheet("模板").doWrite(list2);
	}

}
