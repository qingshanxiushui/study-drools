package org.example.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.example.dto.ParamDto;
import org.example.service.EmailService;
import org.example.service.FeignApi;
import org.example.service.HelloService;
import org.example.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

// swagger2访问连接 http://127.0.0.1:8088/swagger-ui.html

@RestController
@Api(tags = "SwaggerController", description = "SwaggerController | 测试swagger")
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private RuleService ruleService;

    @Autowired
    private HelloService helloService;

    @Autowired
    private FeignApi feignApi;

    @RequestMapping("/rule")  //没有指定具体方法,get，post,put,delete,head,options,patch都可调用
    @ApiOperation(value="rule 方法", notes="hello Swagger测试方法--rule")
    public String rule(){
        ruleService.rule();
        //return "{\"code\": 200,\"msg\": \"查询rule成功\"} ";
        ParamDto paramDto = new ParamDto(1l,5,"中文");
        System.out.println(JSON.toJSONString(paramDto));
        return JSON.toJSONString(paramDto);
    }
    @PostMapping("/template")
    @ApiOperation(value="template 方法", notes="hello Swagger测试方法--template")
    public String template(@ApiParam("code") @RequestParam("code") String code, @RequestParam("message") String message) throws IOException {
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

    @GetMapping("/cache")
    public String getCache(@RequestParam("id") int id){
        return "{\"code\": 600,\"msg\": \""+helloService.getById(id)+"\"} ";
    }

    @Autowired
    private EmailService emailService;

    @GetMapping("/email")
    public void someMethodThatSendsEmail() {
        emailService.sendEmail("yang-qs@neusoft.com", "Test Email", "This is a test email.");
    }

    @GetMapping("/feign")
    public String getFeign(){
        return feignApi.get("100004");
    }
}
