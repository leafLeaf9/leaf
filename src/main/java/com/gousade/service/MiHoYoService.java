package com.gousade.service;

import com.gousade.entity.dto.CqHttpEvent;

public interface MiHoYoService {
    void bindMiHoYoCookie(CqHttpEvent event);

    String doSign(String cookie);
}
