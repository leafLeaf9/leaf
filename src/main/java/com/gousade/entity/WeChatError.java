package com.gousade.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * ΢�Žӿڵ���ʧ��ʱ����ȴ����200�ɹ�����Ҫ���Error����Ϊ΢�������Ļ���
 *
 * @author woxigousade
 * @date 2021/9/27
 */
@Data
public class WeChatError implements Serializable {
    private Integer errcode;
    private String errmsg;
}
