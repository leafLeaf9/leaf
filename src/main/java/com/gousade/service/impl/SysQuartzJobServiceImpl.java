package com.gousade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gousade.entity.SysQuartzJob;
import com.gousade.mapper.SysQuartzJobMapper;
import com.gousade.service.ISysQuartzJobService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度表 服务实现类
 * </p>
 *
 * @author woxigousade
 * @since 2022-07-21
 */
@Service
public class SysQuartzJobServiceImpl extends ServiceImpl<SysQuartzJobMapper, SysQuartzJob> implements ISysQuartzJobService {

}
