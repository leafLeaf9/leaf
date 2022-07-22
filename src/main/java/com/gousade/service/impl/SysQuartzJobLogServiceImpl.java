package com.gousade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gousade.entity.SysQuartzJobLog;
import com.gousade.mapper.SysQuartzJobLogMapper;
import com.gousade.service.ISysQuartzJobLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author woxigousade
 * @since 2022-07-22
 */
@Service
public class SysQuartzJobLogServiceImpl extends ServiceImpl<SysQuartzJobLogMapper, SysQuartzJobLog> implements ISysQuartzJobLogService {

}
