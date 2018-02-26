package common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class SchedulerFactory {

    private static ThreadFactory threadFactory(String pattern) {
        return new ThreadFactoryBuilder().setNameFormat(pattern).build();
    }

    private static final ExecutorService poolUI = Executors.newFixedThreadPool(1, threadFactory("UI_Thread"));
    public static final Scheduler uiScheduler = Schedulers.from(poolUI);

    private static final ExecutorService poolDisk = Executors.newFixedThreadPool(10, threadFactory("Disk-%d"));
    public static final Scheduler diskScheduler = Schedulers.from(poolDisk);

    private static final ExecutorService poolComp = Executors.newFixedThreadPool(10, threadFactory("Comp-%d"));
    public static final Scheduler computationScheduler = Schedulers.from(poolComp);

}
