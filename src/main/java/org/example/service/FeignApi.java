package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "myClient", url = "http://10.33.19.213", fallbackFactory = FeignClientFallBack.class)
public interface FeignApi {
    /**
     * 带一个参数的get请求
     */
    @GetMapping("/gen/get_medical_record")
    String get(@RequestParam("generate_medical_record_id") String generateMedicalRecordId);
}
