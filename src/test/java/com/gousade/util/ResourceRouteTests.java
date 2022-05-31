package com.gousade.util;

import com.gousade.entity.ResourceRouteDO;
import com.gousade.entity.dto.ShortestRouteDTO;
import com.gousade.service.ResourceRouteService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ResourceRouteTests {
    @Autowired
    private ResourceRouteService resourceRouteService;

    @Resource
    private ResourceRouteUtils resourceRouteUtils;

    @Before("")
    public void setUp() {
        testInsertRoutes();
    }

    public void testInsertRoutes() {
        LocalDateTime now = LocalDateTime.now();
        ResourceRouteDO v1v3 = ResourceRouteDO.builder().id(1508694415952994305L).beginPointId("v1").endPointId("v3")
                .distance(10.0).inUse(true).createTime(now).updateTime(now).deleted(false).build();
        ResourceRouteDO v1v5 = new ResourceRouteDO(1508694415982354433L, "v1", "v5", 30.0, true, now, now, false);
        ResourceRouteDO v1v6 = new ResourceRouteDO(1508694415990743042L, "v1", "v6", 100.0, true, now, now, false);
        ResourceRouteDO v2v3 = new ResourceRouteDO(1508694415990743043L, "v2", "v3", 5.0, true, now, now, false);
        ResourceRouteDO v3v4 = new ResourceRouteDO(1508694415990743044L, "v3", "v4", 50.0, true, now, now, false);
        ResourceRouteDO v4v6 = new ResourceRouteDO(1508694415990743045L, "v4", "v6", 10.0, true, now, now, false);
        ResourceRouteDO v5v4 = new ResourceRouteDO(1508694415999131650L, "v5", "v4", 20.0, true, now, now, false);
        ResourceRouteDO v5v6 = new ResourceRouteDO(1508694415999131651L, "v5", "v6", 60.0, true, now, now, false);
        List<ResourceRouteDO> list = new ArrayList<>();
        list.add(v1v3);
        list.add(v1v5);
        list.add(v1v6);
        list.add(v2v3);
        list.add(v3v4);
        list.add(v4v6);
        list.add(v5v4);
        list.add(v5v6);
        resourceRouteService.saveOrUpdateBatch(list);
    }

    @Test
    public void testCalculateShortestRoute() {
        ShortestRouteDTO shortestRouteV1ToV6 = resourceRouteUtils.calculateShortestRoute("v1", "v6");
        Assertions.assertEquals(60.0, shortestRouteV1ToV6.getDistance());
    }
}
