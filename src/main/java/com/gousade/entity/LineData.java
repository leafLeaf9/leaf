package com.gousade.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 折线图-纵轴(y轴)对象
 *
 * @author Administrator
 * @date 2023/02/07
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(description = "折线图-纵轴(y轴)对象")
public class LineData<T> implements Serializable {
    @Schema(description = "折线描述")
    String lineDesc;
    @Schema(description = "折线数据列表")
    private List<T> list;
}
