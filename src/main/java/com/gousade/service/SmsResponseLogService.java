package com.gousade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gousade.pojo.SmsResponseLog;

public interface SmsResponseLogService  extends IService<SmsResponseLog> {

    void testTransactional();

}
