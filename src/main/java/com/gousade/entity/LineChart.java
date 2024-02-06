package com.gousade.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Schema(description = "折线图对象")
public class LineChart<T> implements Serializable {
    @Schema(description = "横轴(x轴)列表")
    private List<String> lateralAxis;
    @Schema(description = "纵轴(y轴)(数据)列表")
    private List<LineData<T>> verticalAxis;
}
