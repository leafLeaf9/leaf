package com.gousade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gousade.entity.ResourceRouteDO;

import java.util.List;

public interface ResourceRouteMapper extends BaseMapper<ResourceRouteDO> {
    List<ResourceRouteDO> listEffectiveRoutes();
}
