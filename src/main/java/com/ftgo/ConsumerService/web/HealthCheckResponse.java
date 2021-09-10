package com.ftgo.ConsumerService.web;

import lombok.Data;

@Data
public class HealthCheckResponse {
    private String message = "OK";
}
