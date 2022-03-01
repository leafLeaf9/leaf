package com.gousade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gousade.entity.RdHistSummary;

import java.util.List;

public interface RdHistSummaryMapper extends BaseMapper<RdHistSummary> {
    List<RdHistSummary> listCount(String deviceType, int type, String startTime, String endTime);

    void testConcurrentUpdate(Long totalFlux);

    void testConcurrentMinus();
}
