package com.gousade.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author woxigousade
 * @date 2021/10/25
 */
@Data
public class TimeCollection implements Serializable {
    @JsonFormat
    private Date date;
    private Instant instant;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private ZonedDateTime zonedDateTime;
}
