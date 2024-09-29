package org.example.service;

import feign.hystrix.FallbackFactory;

public class FeignClientFallBack implements FallbackFactory<FeignApi> {
    @Override
    public FeignApi create(Throwable throwable) {
        return new FeignApi() {
            @Override
            public String get(String generateMedicalRecordId) {
                return "{\"code\": 300,\"msg\": \"feign失败\"} ";
            }

        };
    }
}
