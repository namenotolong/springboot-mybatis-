package com.huyong.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述: pool
 *
 * @author huyong
 * @date 2020-04-10 3:31 下午
 */
public class MessageThreadPool {
    public static ThreadPoolExecutor getPool() {
        return new ThreadPoolExecutor(20, 30, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20), new NamedThreadFactory("message thread"));
    }
}
