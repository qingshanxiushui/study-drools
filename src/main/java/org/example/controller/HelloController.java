package org.example.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.dto.ParamDto;
import org.example.dto.WriteParamsDto;
import org.example.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private RuleService ruleService;
    @RequestMapping("/rule")
    public String rule(){
        ruleService.rule();
        //return "{\"code\": 200,\"msg\": \"查询rule成功\"} ";
        ParamDto paramDto = new ParamDto(1l,5,"中文");
        System.out.println(JSON.toJSONString(paramDto));
        return JSON.toJSONString(paramDto);
    }
    @PostMapping("/template")
    public String template(@RequestParam("code") String code,@RequestParam("message") String message) throws IOException {
        System.out.println("*************template参数:"+code+message);
        ruleService.rule_template();
        return "{\"code\": 300,\"msg\": \"查询template成功\"} ";
    }

    @PostMapping("/student")
    public String student(@RequestBody ParamDto paramDto){
        System.out.println("*************student参数:"+paramDto.toString());
        ruleService.rule_student();
        return "{\"code\": 400,\"msg\": \"查询student成功\"} ";
    }

    @RequestMapping(value = "/json", method= RequestMethod.POST)
    public String jsonPostMethod(@RequestBody JSONObject content){
        System.out.println("*************json参数:"+content.toString());
        //WriteParamsDto writeParamsDto = JSONObject.toJavaObject(content, WriteParamsDto.class);
        //System.out.println(writeParamsDto.getRecords().get(0).getColumns().get(0).toString());
        //System.out.println(writeParamsDto.getRecords().get(0).getColumns().get(0).getRawData().toString());
//        ParamDto paramDto = JSONObject.parseObject(writeParamsDto.getRecords().get(0).getColumns().get(0).getRawData().toString(), ParamDto.class);
//        System.out.println(paramDto.getChinese());
        return "{\"code\": 500,\"msg\": \"查询json成功\"} ";
    }

}
