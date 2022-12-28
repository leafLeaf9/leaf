package com.gousade.controller;

import com.gousade.common.ResponseResult;
import com.gousade.service.EasyExcelDataService;
import com.gousade.service.SmsResponseLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author woxigousade
 * @date 2021/6/1
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin/transaction")
public class TransactionController {

    @Autowired
    private SmsResponseLogService smsResponseLogService;

    @Autowired
    private EasyExcelDataService easyExcelDataService;

    @GetMapping("/testTransactional")
    public ResponseResult testTransactional() {
        smsResponseLogService.testTransactional();
        return ResponseResult.renderSuccess();
    }

    @GetMapping("/testRequiredCatchInOuterTransaction")
    public ResponseResult testRequiredCatchInOuterTransaction() {
        smsResponseLogService.testRequiredCatchInOuterTransaction();
        return ResponseResult.renderSuccess();
    }

    @GetMapping("/testCallThisClassMethod")
    public ResponseResult testCallThisClassMethod() {
        smsResponseLogService.testCallThisClassMethod();
        return ResponseResult.renderSuccess();
    }

    @GetMapping("/testCallThisClassMethodWithoutTransactional")
    public ResponseResult testCallThisClassMethodWithoutTransactional() {
        smsResponseLogService.testCallThisClassMethodWithoutTransactional();
        return ResponseResult.renderSuccess();
    }
}
