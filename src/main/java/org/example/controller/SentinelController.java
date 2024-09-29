package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class SentinelController {
    @SentinelResource(value = "hello", blockHandler = "handleBlock")
    public String hello() {
        // 业务逻辑
        return "Hello, Sentinel!";
    }

    public String handleBlock() {
        // 处理流量控制触发时的逻辑
        return "Flow control triggered!";
    }
}
