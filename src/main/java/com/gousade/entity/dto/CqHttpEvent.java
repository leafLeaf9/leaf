package com.gousade.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.gousade.util.DateUtils;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

@ApiModel(description = "cqHttp的事件消息实体类")
@Data
public class CqHttpEvent implements Serializable {
    private long time;
    @Getter(value = AccessLevel.NONE)
    private ZonedDateTime receiveTime;
    private String selfId;
    private String postType;
    private String messageType;
    private String subType;
    private int tempSource;
    private int messageId;
    private String groupId;
    private String userId;
    private String anonymous;
    private String message;
    private String rawMessage;
    private int font;
    private JSONObject sender;

    public ZonedDateTime getReceiveTime() {
        return DateUtils.dateToZonedDateTime(new Date(time * 1000));
    }
}
