package com.gousade.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "cqHttp的QQ用户实体类")
@Data
public class CqTencentQQMember implements Serializable {
    @ApiModelProperty(value = "群号")
    private String groupId;

    @ApiModelProperty(value = "QQ号")
    private String userId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "群名片/备注")
    private String card;

    @ApiModelProperty(value = "性别, male 或 female 或 unknown")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private int age;

    @ApiModelProperty(value = "地区")
    private String area;

    @ApiModelProperty(value = "加群时间戳, 秒级")
//    @JSONField(deserializeUsing = Void.class)
    private long joinTime;

    @ApiModelProperty(value = "最后发言时间戳, 秒级")
    private int lastSentTime;

    @ApiModelProperty(value = "成员等级")
    private String level;

    @ApiModelProperty(value = "角色, owner 或 admin 或 member")
    private String role;

    @ApiModelProperty(value = "是否不良记录成员")
    private Boolean unfriendly;

    @ApiModelProperty(value = "专属头衔")
    private String title;

    @ApiModelProperty(value = "专属头衔过期时间戳")
    private String titleExpireTime;

    @ApiModelProperty(value = "是否允许修改群名片")
    private boolean cardChangeable;

    @ApiModelProperty(value = "禁言到期时间")
    private long shutUpTimestamp;
}
