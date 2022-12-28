package com.gousade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gousade.mapper.SmsResponseLogMapper;
import com.gousade.pojo.SmsResponseLog;
import com.gousade.service.EasyExcelDataService;
import com.gousade.service.SmsResponseLogService;
import com.gousade.util.SaltUtil;
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

    /**
     * 捕获异常时候不会回滚，抛出异常可以正常回滚
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransactional() {
        try {
            SmsResponseLog smsResponseLog = SmsResponseLog.builder().id(SaltUtil.generateUUId()).build();
            baseMapper.insert(smsResponseLog);
            throw new RuntimeException("抛出异常测试回滚");
        } catch (Exception e) {
            log.error("捕获异常", e);
//            throw e;
        }
    }

    /**
     * 测试嵌套事务-内层方法抛出异常，外层方法捕获
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testRequiredCatchInOuterTransaction() {
       /* easyExcelDataService.save(EasyExcelData.builder().id(SaltUtil.generateUUId()).build());
        try {
            //smsResponseLogService.testTransactionalTryCatch();
            ((SmsResponseLogServiceImpl) AopContext.currentProxy()).testTransactionalThrowException();
//            SmsResponseLogService smsResponseLogService = SpringBeanUtil.getBean(SmsResponseLogService.class);
//            smsResponseLogService.testTransactionalThrowException();
        } catch (Exception e) {
            log.error("捕获异常", e);
        }*/
        testTransactionalThrowException();

    }

    @Transactional(rollbackFor = Exception.class)
    public void testTransactionalThrowException() {
        SmsResponseLog smsResponseLog = SmsResponseLog.builder().id(SaltUtil.generateUUId()).build();
        baseMapper.insert(smsResponseLog);
        throw new RuntimeException("抛出异常测试回滚");
    }

    /**
     * 测试调用本类方法
     * 本方法添加了Transactional注解，所以可以回滚
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testCallThisClassMethod() {
        testTransactionalThrowException();
    }

    /**
     * 测试调用本类方法，不使用事务注解
     * 本方法未添加Transactional注解，所以无法回滚
     * <p>
     * 直接调用同类下方法@Transactional不生效，可以通过三种方法解决
     * 1、@Autowired自己
     * 2、启动类添加@EnableAspectJAutoProxy(exposeProxy = true)，使用((Class)AopContext.currentProxy()).method();使用@Async时，可能失效
     * 3、自定义SpringBeanUtil获取bean
     *
     * @link <a href="https://docs.spring.io/spring-framework/docs/5.0.9.RELEASE/spring-framework-reference/data-access.html#tx-propagation-required">...</a>
     * @see org.springframework.context.annotation.EnableAspectJAutoProxy
     */
    @Override
    public void testCallThisClassMethodWithoutTransactional() {
        testTransactionalThrowException();
//        smsResponseLogService.testCallThisClassMethodWithoutTransactional();
//        ((SmsResponseLogServiceImpl) AopContext.currentProxy()).testCallThisClassMethodWithoutTransactional();
//            SmsResponseLogService smsResponseLogService = SpringBeanUtil.getBean(SmsResponseLogService.class);
//            smsResponseLogService.testCallThisClassMethodWithoutTransactional();
    }
}
