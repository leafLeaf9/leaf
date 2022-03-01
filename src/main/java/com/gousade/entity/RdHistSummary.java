package com.gousade.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 交调(包括etc)历史数据统计实体类
 *
 * @author chuzizhuo
 * @date 2022/1/20
 */
@Schema(description = "交调(包括etc)历史数据统计实体类")
public class RdHistSummary implements Serializable {

    @TableId
    private String id;

    /**
     * 设备编号
     */
    @Schema(description = "设备编号")
    private String deviceId;

    /**
     * 设备类型 rdDevice ETCRdDevice
     */
    @Schema(description = "设备类型 rdDevice ETCRdDevice")
    private String deviceType;

    /**
     * 车道号 单车道：上行 01，下行03。 2车道以上：上行从内至外按 11、12、13…编号；下行按 31、32、33…编号
     */
    @Schema(description = "车道号 单车道：上行 01，下行03。 2车道以上：上行从内至外按 11、12、13…编号；下行按 31、32、33…编号")
    private int lineNo;
    /**
     * 统计类型 5min(5),30min(30)
     */
    @Schema(description = "统计类型 5min(5),30min(30)")
    private int type;
    /**
     * 时间,格式为HH:mm
     */
    @Schema(description = "时间,格式为HH:mm")
    private String time;
    /**
     * 总占有率
     */
    @Schema(description = "总占有率")
    private Double totalOccupancy;
    /**
     * 总交通量
     */
    @Schema(description = "总交通量")
    private Long totalFlux;
    /**
     * 总里程
     */
    @Schema(description = "总里程")
    private Double totalMileage;
    /**
     * 数据总条数
     */
    @Schema(description = "数据总条数")
    private Long count;
    /**
     * 占有率
     */
    @Schema(description = "平均占有率")
    private Double occupancy;
    /**
     * 交通量(总交通量除以总条数)
     */
    @Schema(description = "平均交通量(总交通量除以总条数)")
    private Long flux;
    /**
     * 平均速度(总里程除以总交通量)
     */
    @Schema(description = "平均速度(总里程除以总交通量)")
    private Double speed;
    /**
     * 客车总交通量
     */
    @Schema(description = "客车总交通量")
    private Long carTotalFlux;
    /**
     * 客车总里程
     */
    @Schema(description = "客车总里程")
    private Double carTotalMileage;
    /**
     * 客车平均交通量(总交通量除以总条数)
     */
    @Schema(description = "客车平均交通量(总交通量除以总条数)")
    private Long carFlux;
    /**
     * 客车平均速度(总里程除以总交通量)
     */
    @Schema(description = "客车平均速度(总里程除以总交通量)")
    private Double carSpeed;
    /**
     * 货车总交通量
     */
    @Schema(description = "货车总交通量")
    private Long truckTotalFlux;
    /**
     * 货车总里程
     */
    @Schema(description = "货车总里程")
    private Double truckTotalMileage;
    /**
     * 货车平均交通量(总交通量除以总条数)
     */
    @Schema(description = "货车平均交通量(总交通量除以总条数)")
    private Long truckFlux;
    /**
     * 货车平均速度(总里程除以总交通量)
     */
    @Schema(description = "货车平均速度(总里程除以总交通量)")
    private Double truckSpeed;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public void getAreaAndLaneNo() {
        String area;
        int laneNo = 0;
        if ("01,11,12,13,14,15,16,17,18,19".contains(String.valueOf(lineNo))) {
            area = "1";
        } else {
            area = "2";
        }
        if ("1".equals(area)) {
            laneNo = lineNo % 10;
        } else {
            laneNo = lineNo % 30;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTotalOccupancy() {
        return totalOccupancy;
    }

    public void setTotalOccupancy(Double totalOccupancy) {
        this.totalOccupancy = totalOccupancy;
    }

    public Long getTotalFlux() {
        return totalFlux;
    }

    public void setTotalFlux(Long totalFlux) {
        this.totalFlux = totalFlux;
    }

    public Double getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(Double totalMileage) {
        this.totalMileage = totalMileage;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Double occupancy) {
        this.occupancy = occupancy;
    }

    public Long getFlux() {
        return flux;
    }

    public void setFlux(Long flux) {
        this.flux = flux;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Long getCarTotalFlux() {
        return carTotalFlux;
    }

    public void setCarTotalFlux(Long carTotalFlux) {
        this.carTotalFlux = carTotalFlux;
    }

    public Double getCarTotalMileage() {
        return carTotalMileage;
    }

    public void setCarTotalMileage(Double carTotalMileage) {
        this.carTotalMileage = carTotalMileage;
    }

    public Long getCarFlux() {
        return carFlux;
    }

    public void setCarFlux(Long carFlux) {
        this.carFlux = carFlux;
    }

    public Double getCarSpeed() {
        return carSpeed;
    }

    public void setCarSpeed(Double carSpeed) {
        this.carSpeed = carSpeed;
    }

    public Long getTruckTotalFlux() {
        return truckTotalFlux;
    }

    public void setTruckTotalFlux(Long truckTotalFlux) {
        this.truckTotalFlux = truckTotalFlux;
    }

    public Double getTruckTotalMileage() {
        return truckTotalMileage;
    }

    public void setTruckTotalMileage(Double truckTotalMileage) {
        this.truckTotalMileage = truckTotalMileage;
    }

    public Long getTruckFlux() {
        return truckFlux;
    }

    public void setTruckFlux(Long truckFlux) {
        this.truckFlux = truckFlux;
    }

    public Double getTruckSpeed() {
        return truckSpeed;
    }

    public void setTruckSpeed(Double truckSpeed) {
        this.truckSpeed = truckSpeed;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGroupString() {
        return getDeviceId() + "," + getTime() + "," + getLineNo();
    }

    public Integer getHour() {
        return StringUtils.isEmpty(time) ? 0 : Integer.parseInt(time.substring(0, 2));
    }

    @Schema(description = "方向 0无效 1上行 2下行")
    public int getArea() {
        if (lineNo == 0) {
            return 0;
        }
        if ("01,11,12,13,14,15,16,17,18,19".contains(String.valueOf(lineNo))) {
            return 1;
        } else {
            return 2;
        }
    }
}
