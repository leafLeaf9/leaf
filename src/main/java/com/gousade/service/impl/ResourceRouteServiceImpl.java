package com.gousade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gousade.entity.ResourceRouteDO;
import com.gousade.mapper.ResourceRouteMapper;
import com.gousade.service.ResourceRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResourceRouteServiceImpl extends ServiceImpl<ResourceRouteMapper, ResourceRouteDO> implements ResourceRouteService {
    @Override
    public List<ResourceRouteDO> listEffectiveRoutes() {
        return baseMapper.listEffectiveRoutes();
    }
}
