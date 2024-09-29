package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
// 指定默认熔断方法
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixTestController {
    /**
     * 用法1：直接使用@HystrixCommand注解，使用默认熔断方法
     */
    @HystrixCommand
    @GetMapping("/test1")
    public String test1() throws InterruptedException {
        throw new RuntimeException("测试异常");
    }

    /**
     * 用法2：使用@HystrixCommand注解，指定熔断方法和超时时间
     */
    @HystrixCommand(
            fallbackMethod = "myFallback", // 降级的回调方法
            commandProperties = {
                    // 设置超时时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            })
    @GetMapping("/test2")
    public String test2() throws InterruptedException {
//        throw new RuntimeException("测试异常");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + 1);
            Thread.sleep(1000);
        }
        return "{\"code\": 200,\"msg\": \"hystrix test2 成功\"} ";
    }

    /**
     * 该方法是一个熔断方法，当方法出现异常时，会调用该方法
     */
    public String defaultFallback() {
        return "{\"code\": 300,\"msg\": \"@DefaultProperties指定的熔断方法\"} ";
    }

    /**
     * 该方法是一个熔断方法，当方法出现异常时，会调用该方法
     */
    public String myFallback() {
        return "{\"code\": 400,\"msg\": \"@HystrixCommand指定的熔断方法\"} ";
    }
}
