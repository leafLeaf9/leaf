package com.gousade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gousade.entity.ResourceRouteDO;

import java.util.List;

public interface ResourceRouteService extends IService<ResourceRouteDO> {
    List<ResourceRouteDO> listEffectiveRoutes();
}
