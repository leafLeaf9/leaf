package com.gousade.java8;


import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class KnowledgeBaseResourceInfo implements Serializable {
    @Schema(description = "小组名称", name = "groupName")
    private String groupName;

    @Schema(description = "部门", name = "department", example = "交警")
    private String department;

    @Schema(description = "出发地", name = "startPosition")
    private BigDecimal startPosition;

    @Schema(description = "开始桩号", name = "beginPosition")
    private BigDecimal beginPosition;

    @Schema(description = "截至桩号", name = "endPosition")
    private BigDecimal endPosition;

    @Schema(description = "方向", name = "direction")
    private String direction;

    @Schema(description = "路线规划方案", name = "pathScheme")
    private String pathScheme;

    @Schema(description = "任务明细列表", name = "taskDetail")
    private List<String> taskDetails;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(BigDecimal startPosition) {
        this.startPosition = startPosition;
    }

    public BigDecimal getBeginPosition() {
        return beginPosition;
    }

    public void setBeginPosition(BigDecimal beginPosition) {
        this.beginPosition = beginPosition;
    }

    public BigDecimal getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(BigDecimal endPosition) {
        this.endPosition = endPosition;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPathScheme() {
        return pathScheme;
    }

    public void setPathScheme(String pathScheme) {
        this.pathScheme = pathScheme;
    }

    public List<String> getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(List<String> taskDetails) {
        this.taskDetails = taskDetails;
    }


}
