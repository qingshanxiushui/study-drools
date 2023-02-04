package org.example.controller;

import org.example.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private RuleService ruleService;
    @RequestMapping("/rule")
    public String rule(){
        ruleService.rule();
        return "{\"code\": 200,\"msg\": \"查询成功\"} ";
    }
    @PostMapping("/template")
    public String template(@RequestParam("code") String code) throws IOException {
        System.out.println("*************参数:"+code);
        ruleService.rule_template();
        return "OK";
    }

    @RequestMapping("/student")
    public String student(){
        ruleService.rule_student();
        return "Ok";
    }
}
