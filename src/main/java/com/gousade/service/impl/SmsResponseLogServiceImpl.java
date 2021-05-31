package com.gousade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gousade.mapper.SmsResponseLogMapper;
import com.gousade.pojo.SmsResponseLog;
import com.gousade.service.SmsResponseLogService;
import com.gousade.utils.SaltUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chuzizhuo
 * @date 2021/5/31
 */
@Service
@Slf4j
public class SmsResponseLogServiceImpl extends ServiceImpl<SmsResponseLogMapper, SmsResponseLog> implements SmsResponseLogService {


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransactional() {
        try {
            SmsResponseLog smsResponseLog = SmsResponseLog.builder().id(SaltUtil.generateUUId()).build();
            baseMapper.insert(smsResponseLog);
            throw new RuntimeException("抛出异常测试回滚");
        } catch (Exception e) {
            log.error("捕获异常", e);
            throw e;
        }
    }
}
