package com.huyong.thread;


import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 描述: pool
 *
 * @author huyong
 * @date 2020-04-10 3:23 下午
 */
public class MessageSchedule {
    public static ScheduledExecutorService getPool() {
        return new ScheduledThreadPoolExecutor(20, new NamedThreadFactory("消息定时任务"));
    }
}
