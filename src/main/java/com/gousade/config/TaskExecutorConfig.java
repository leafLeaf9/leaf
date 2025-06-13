package com.gousade.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

//@AutoConfiguration
@Configuration
public class TaskExecutorConfig implements AsyncConfigurer {
    public static final int cpuNum = Runtime.getRuntime().availableProcessors();

    @Value("${thread.pool.corePoolSize:}")
    private Optional<Integer> corePoolSize;

    @Value("${thread.pool.maxPoolSize:}")
    private Optional<Integer> maxPoolSize;

    @Value("${thread.pool.queueCapacity:}")
    private Optional<Integer> queueCapacity;

    @Value("${thread.pool.awaitTerminationSeconds:}")
    private Optional<Integer> awaitTerminationSeconds;

    @Override
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程大小 默认区 CPU 数量
        executor.setCorePoolSize(corePoolSize.orElse(cpuNum));
        // 最大线程大小 默认区 CPU * 2 数量
        executor.setMaxPoolSize(maxPoolSize.orElse(cpuNum * 2));
        // 队列最大容量
        executor.setQueueCapacity(queueCapacity.orElse(500));
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds.orElse(60));
        executor.setThreadNamePrefix("AsyncEvent-Thread-");
        // 设置拒绝策略
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy()); // 默认策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        executor.initialize();
        return executor;
    }
}
