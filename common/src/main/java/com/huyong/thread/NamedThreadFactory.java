package com.huyong.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huyong
 */
public class NamedThreadFactory implements ThreadFactory {

    private AtomicInteger threadNum = new AtomicInteger(1);


    private String namePrefix;

    private boolean isDaemon;

    public NamedThreadFactory(String namePrefix){

        this.namePrefix = namePrefix;
        this.isDaemon = false;
    }

    public NamedThreadFactory(String namePrefix, Boolean isDaemon){

        this.namePrefix = namePrefix;
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(namePrefix + "-" + threadNum.getAndIncrement());
        thread.setDaemon(isDaemon);
        return thread;
    }
}

    
