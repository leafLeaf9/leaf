package com.gousade.entity.dto;

import com.gousade.util.Java8DateUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

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
    private String sender;

    public ZonedDateTime getReceiveTime() {
        return Java8DateUtil.dateToZonedDateTime(new Date(time * 1000));
    }
}
