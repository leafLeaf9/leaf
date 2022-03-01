package com.gousade.controller;

import com.gousade.common.ResponseResult;
import com.gousade.entity.RdHistSummary;
import com.gousade.service.RdHistSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/gousadeTest")
@CrossOrigin
public class RdHistSummaryController {
    @Autowired
    private RdHistSummaryService rdHistSummaryService;

    @GetMapping("list")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult list(@RequestParam String deviceType,
                               @RequestParam int type, @RequestParam String startTime, @RequestParam String endTime) {
        List<RdHistSummary> list = rdHistSummaryService.listCount(deviceType, type, startTime, endTime);
        return ResponseResult.renderSuccess().data("list", list);
    }

    @GetMapping("testConcurrentUpdate")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult testConcurrentUpdate() {
        for (int i = 0; i < 30; i++) {
            Long totalFlux = (long) i;
            new Thread(() -> rdHistSummaryService.testConcurrentUpdate(totalFlux)).start();
        }

        return ResponseResult.renderSuccess();
    }

    @GetMapping("testConcurrentMinus")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult testConcurrentMinus() {
        rdHistSummaryService.testConcurrentUpdate(30L);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> rdHistSummaryService.testConcurrentMinus()).start();
        }
        return ResponseResult.renderSuccess();
    }
}
