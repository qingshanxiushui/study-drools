package org.example.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class testTask {
    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay");
    }

    @Async //异步执行
    @Scheduled(cron = "0/2 * * * * ? ")
    public void index1() {
        System.out.println("定时任务1，2秒执行一次，time：" + System.currentTimeMillis() + " 线程：" + Thread.currentThread().getName());
    }
}
