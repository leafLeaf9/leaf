package com.gousade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gousade.entity.RdHistSummary;
import com.gousade.mapper.RdHistSummaryMapper;
import com.gousade.service.RdHistSummaryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RdHistSummaryServiceImpl extends ServiceImpl<RdHistSummaryMapper, RdHistSummary> implements RdHistSummaryService {
    @Override
    public List<RdHistSummary> listCount(String deviceType, int type, String startTime, String endTime) {
        return baseMapper.listCount(deviceType, type, startTime, endTime);
    }

    @Override
    public void testConcurrentUpdate(Long totalFlux) {
        baseMapper.testConcurrentUpdate(totalFlux);
    }

    @Override
    public void testConcurrentMinus() {
        baseMapper.testConcurrentMinus();
    }
}
