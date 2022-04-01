package com.gousade.entity.dto;

import com.gousade.util.ResourceRouteUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@Data
public class ResourceRouteMatrix {
    private int pointCount;
    private double[][] distanceMatrix;
    private ShortestRouteDTO[] routeArray;

    public ResourceRouteMatrix(int pointCount) {
        this.pointCount = pointCount;
        distanceMatrix = new double[pointCount][pointCount];
        routeArray = new ShortestRouteDTO[pointCount];
    }

    public void printMatrix() {
        for (double[] distanceMatrix : getDistanceMatrix()) {
            System.out.println(Arrays.toString(distanceMatrix));
        }
    }

    public void printRouteArray() {
        for (ShortestRouteDTO routeDTO : getRouteArray()) {
            String distanceDesc = Double.compare(routeDTO.getDistance(), ResourceRouteUtils.INFINITE_DISTANCE) == 0
                    ? "∞"
                    : String.valueOf(routeDTO.getDistance());
            System.out.println(routeDTO.getTarget() + ": " + routeDTO.getStringPath() + " = " + distanceDesc);
        }
    }
}
