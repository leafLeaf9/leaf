package com.gousade.controller;


import com.gousade.annotation.OperationRecord;
import com.gousade.commonutils.ResponseResult;
import com.gousade.service.EasyExcelDataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author woxigousade <woxigsd@gmail.com>
 * @since 2020-09-25
 */
@Api(tags = "easyExcelData")
@RestController
@RequestMapping("/easyExcelData")
@CrossOrigin
public class EasyExcelDataController {

    @Autowired
    private EasyExcelDataService easyExcelDataService;

    /**
     * 文件上传
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>
     * 3. 直接读即可
     */
    @OperationRecord(operationNum = 1, operationDescription = "上传excel测试")
    @PostMapping("upload")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult upload(MultipartFile file) throws IOException {
		/*EasyExcel.read(file.getInputStream(), EasyExcelData.class, new EasyExcelDataListener(easyExcelDataService)).sheet().doRead();
		return ResponseResult.renderSuccess().message("上传成功");*/
        return easyExcelDataService.upload(file, easyExcelDataService);
    }

    @OperationRecord(operationNum = 2, operationDescription = "下载excel测试")
    @PostMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        easyExcelDataService.download(response, easyExcelDataService);
    }

    @GetMapping("downloadNoFailure")
    public void downloadNoFailure(HttpServletResponse response) throws IOException {
        easyExcelDataService.downloadNoFailure(response, easyExcelDataService);
    }

}

