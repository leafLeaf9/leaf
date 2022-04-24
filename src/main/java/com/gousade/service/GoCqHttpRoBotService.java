package com.gousade.service;

import com.gousade.entity.dto.CqHttpEvent;

public interface GoCqHttpRoBotService {
    void handleCqHttpEvent(CqHttpEvent event);

    void miHoYoSign(String userId, String groupId);

    void sendGroupMsg(String groupId, String message);
}
