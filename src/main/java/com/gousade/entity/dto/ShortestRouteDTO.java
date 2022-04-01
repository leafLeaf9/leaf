package com.gousade.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.gousade.entity.ResourceRouteDO;
import lombok.Data;

import java.util.List;

@Data
public class ShortestRouteDTO {
    private List<ResourceRouteDO> path;
    private String stringPath;
    private String target;
    private Double distance;
    @JSONField(serialize = false)
    private boolean visit;
}
