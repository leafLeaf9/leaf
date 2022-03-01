package com.gousade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gousade.entity.RdHistSummary;

import java.util.List;


public interface RdHistSummaryService extends IService<RdHistSummary> {

    List<RdHistSummary> listCount(String deviceType, int type, String startTime, String endTime);

    void testConcurrentUpdate(Long totalFlux);

    void testConcurrentMinus();
}
