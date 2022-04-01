package com.gousade.util;


import com.gousade.entity.ResourceRouteDO;
import com.gousade.entity.dto.ResourceRouteMatrix;
import com.gousade.entity.dto.ShortestRouteDTO;
import com.gousade.service.ResourceRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 资源路径规划工具类，Dijkstra算法实现
 * https://blog.csdn.net/qq_35644234/article/details/60870719
 */
@Component
public class ResourceRouteUtils {
    public static final double INFINITE_DISTANCE = Double.MAX_VALUE;

    @Autowired
    private ResourceRouteService resourceRouteService;

    public ShortestRouteDTO calculateShortestRoute(String beginPointId, String endPointId) {
        ShortestRouteDTO result = new ShortestRouteDTO();

        List<ResourceRouteDO> routeList = resourceRouteService.listEffectiveRoutes();
        List<String> pointIds = buildPointIds(routeList);
        int beginIndex = pointIds.indexOf(beginPointId);
        if (beginIndex < 0) {
            throw new RuntimeException("资源邻接矩阵路径表没有该起始资源点，请检查配置。");
        }

        Map<String, ResourceRouteDO> dataMap = getRouteDOMap(routeList);
        ResourceRouteMatrix matrix = buildRouteMatrix(beginIndex, pointIds, routeList);

        dijkstra(pointIds, dataMap, matrix);

        result.setPath(new ArrayList<>());
        int endIndex = pointIds.indexOf(endPointId);
        if (endIndex >= 0) {
            BeanUtils.copyProperties(matrix.getRouteArray()[endIndex], result);
        }
        return result;
    }


    private Map<String, ResourceRouteDO> getRouteDOMap(List<ResourceRouteDO> routeList) {
        return routeList.stream().collect(Collectors.toConcurrentMap(
                e -> e.getBeginPointId() + "->" + e.getEndPointId(), Function.identity()));
    }

    private List<String> buildPointIds(List<ResourceRouteDO> routeList) {
        List<String> beginPointIds = routeList.stream().map(ResourceRouteDO::getBeginPointId)
                .distinct().collect(Collectors.toList());
        List<String> endPointIds = routeList.stream().map(ResourceRouteDO::getEndPointId)
                .distinct().collect(Collectors.toList());
        beginPointIds.addAll(endPointIds);
        return beginPointIds.stream().distinct().collect(Collectors.toList());
    }

    private ResourceRouteMatrix buildRouteMatrix(int beginIndex, List<String> pointIds, List<ResourceRouteDO> routeList) {
        Map<String, ResourceRouteDO> dataMap = getRouteDOMap(routeList);
        Map<String, Double> routeMap = routeList.stream().collect(Collectors.toConcurrentMap(
                e -> e.getBeginPointId() + "->" + e.getEndPointId(), ResourceRouteDO::getDistance));
        int pointCount = pointIds.size();
        ResourceRouteMatrix matrix = new ResourceRouteMatrix(pointCount);
        //构造基础矩阵
        for (int i = 0; i < pointCount; i++) {
            for (int j = 0; j < pointCount; j++) {
                String key = pointIds.get(i) + "->" + pointIds.get(j);
                double distance = i == j ? 0.0 : routeMap.getOrDefault(key, INFINITE_DISTANCE);
                matrix.getDistanceMatrix()[i][j] = distance;
            }
        }
        matrix.printMatrix();
        initMatrixRouteArray(beginIndex, pointIds, matrix, dataMap);
        return matrix;
    }

    private void initMatrixRouteArray(int beginIndex, List<String> pointIds, ResourceRouteMatrix matrix,
                                      Map<String, ResourceRouteDO> dataMap) {
        int pointCount = pointIds.size();
        for (int j = 0; j < pointCount; j++) {
            ShortestRouteDTO routeDTO = new ShortestRouteDTO();
            routeDTO.setDistance(matrix.getDistanceMatrix()[beginIndex][j]);
            String key = pointIds.get(beginIndex) + "->" + pointIds.get(j);
            String path = key + "(" + routeDTO.getDistance() + ")";
            routeDTO.setTarget(key);
            routeDTO.setStringPath(path);
            routeDTO.setPath(new ArrayList<>());
            ResourceRouteDO resourceRouteDO = dataMap.get(key);
            if (resourceRouteDO != null) {
                routeDTO.getPath().add(resourceRouteDO);
            }
            matrix.getRouteArray()[j] = routeDTO;
        }
        //到本身直接算作访问过
        matrix.getRouteArray()[beginIndex].setVisit(true);
    }

    private void dijkstra(List<String> pointIds, Map<String, ResourceRouteDO> dataMap, ResourceRouteMatrix matrix) {
        int pointCount = pointIds.size();
        int count = 1;
        while (count < matrix.getPointCount()) {
            //temp用于保存当前dis数组中最小的那个下标
            //min记录的当前的最小值
            int temp = 0;
            double min = INFINITE_DISTANCE;
            for (int i = 0; i < pointCount; i++) {
                ShortestRouteDTO dto = matrix.getRouteArray()[i];
                if (!dto.isVisit() && Double.compare(dto.getDistance(), INFINITE_DISTANCE) != 0
                        && Double.compare(dto.getDistance(), min) < 0) {
                    min = dto.getDistance();
                    temp = i;
                }
            }
            //把temp对应的顶点加入到已经找到的最短路径的集合中
            matrix.getRouteArray()[temp].setVisit(true);
            count++;

            for (int i = 0; i < pointCount; i++) {
                ShortestRouteDTO[] routeArray = matrix.getRouteArray();
                double[][] distanceMatrix = matrix.getDistanceMatrix();
                BigDecimal bigDecimal = BigDecimal.valueOf(routeArray[temp].getDistance())
                        .add(BigDecimal.valueOf(distanceMatrix[temp][i]));
                if (!routeArray[i].isVisit() && Double.compare(distanceMatrix[temp][i], INFINITE_DISTANCE) != 0
                        && bigDecimal.compareTo(BigDecimal.valueOf(routeArray[i].getDistance())) < 0) {
                    //如果新得到的边可以影响其他未访问的顶点，那就就更新它的最短路径和长度
                    routeArray[i].setDistance(bigDecimal.doubleValue());
                    String path = routeArray[temp].getStringPath() + "->" + pointIds.get(i);
                    List<String> points = new ArrayList<>(Arrays.asList(routeArray[temp].getStringPath().split("->")));
                    String key = points.get(points.size() - 1).split("\\(")[0] + "->" + pointIds.get(i);
                    routeArray[i].setStringPath(path + "(" + distanceMatrix[temp][i] + ")");
                    routeArray[i].setPath(new ArrayList<>(routeArray[temp].getPath()));
                    routeArray[i].getPath().add(dataMap.get(key));
                }
            }
        }
        matrix.printRouteArray();
    }
}
