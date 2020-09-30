package com.gousade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gousade.commonutils.ResponseResult;
import com.gousade.entity.EasyExcelData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author woxigousade <woxigsd@gmail.com>
 * @since 2020-09-25
 */
public interface EasyExcelDataService extends IService<EasyExcelData> {

	ResponseResult upload(MultipartFile file, EasyExcelDataService easyExcelDataService) throws IOException;

	void download(HttpServletResponse response, EasyExcelDataService easyExcelDataService) throws IOException;

	void downloadNoFailure(HttpServletResponse response, EasyExcelDataService easyExcelDataService) throws IOException;

}
