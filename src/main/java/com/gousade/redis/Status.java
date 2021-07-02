package com.gousade.redis;

import java.util.concurrent.TimeUnit;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-12 15:16:04
 */
public abstract class Status {

    /**
     * 过期时间相关枚举
     */
    public enum ExpireEnum {
        // 未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS);

        /**
         * 过期时间
         */
        private final Long time;
        /**
         * 时间单位
         */
        private final TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}
