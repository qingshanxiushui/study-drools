package org.example.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class testTask {

    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final AtomicBoolean running = new AtomicBoolean(false);
    private static int i = 0;

    //@Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay"+dateformat.format(System.currentTimeMillis()));
    }

    @Async //异步执行
    @Scheduled(cron = "0/4 * * * * ? ")
    public void index1() {
        System.out.println("定时任务1，2秒执行一次，time：" + dateformat.format(System.currentTimeMillis()) + " 线程：" + Thread.currentThread().getName());
        i++;
        System.out.println("i="+i);
        if(i%2==0){
            running.set(true);
        }else{
            running.set(false);
        }
        System.out.println("boolean="+running.get());
    }
}
