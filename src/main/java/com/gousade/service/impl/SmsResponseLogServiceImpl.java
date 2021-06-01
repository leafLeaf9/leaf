package com.gousade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gousade.entity.EasyExcelData;
import com.gousade.mapper.SmsResponseLogMapper;
import com.gousade.pojo.SmsResponseLog;
import com.gousade.service.EasyExcelDataService;
import com.gousade.service.SmsResponseLogService;
import com.gousade.utils.SaltUtil;
import com.gousade.utils.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author woxigousade
 * @date 2021/5/31
 */
@Service
@Slf4j
public class SmsResponseLogServiceImpl extends ServiceImpl<SmsResponseLogMapper, SmsResponseLog> implements SmsResponseLogService {

    @Autowired
    private EasyExcelDataService easyExcelDataService;

    /*@Autowired
    private SmsResponseLogService smsResponseLogService;*/

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

    /**
     * 测试嵌套事务-内层方法抛出异常，外层方法捕获
     * 直接调用同类下方法@Transactional不生效，可以通过三种方法解决
     * 1、@Autowired自己
     * 2、启动类添加@EnableAspectJAutoProxy(exposeProxy = true)，使用((Class)AopContext.currentProxy()).method();使用@Async时，可能失效
     * 3、自定义SpringBeanUtil获取bean
     * @see org.springframework.context.annotation.EnableAspectJAutoProxy
     * @link https://docs.spring.io/spring-framework/docs/5.0.9.RELEASE/spring-framework-reference/data-access.html#tx-propagation-required
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testRequiredCatchInOuterTransaction() {
        easyExcelDataService.save(EasyExcelData.builder().id(SaltUtil.generateUUId()).build());
        try {
            //smsResponseLogService.testTransactionalTryCatch();
            //((SmsResponseLogServiceImpl)AopContext.currentProxy()).testTransactionalTryCatch();
            SmsResponseLogService smsResponseLogService = SpringBeanUtil.getBean(SmsResponseLogService.class);
            smsResponseLogService.testTransactionalThrowException();
        } catch (Exception e) {
            log.error("捕获异常", e);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void testTransactionalThrowException() {
            SmsResponseLog smsResponseLog = SmsResponseLog.builder().id(SaltUtil.generateUUId()).build();
            baseMapper.insert(smsResponseLog);
            throw new RuntimeException("抛出异常测试回滚");
    }
}
