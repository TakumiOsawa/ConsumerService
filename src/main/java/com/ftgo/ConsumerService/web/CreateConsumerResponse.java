package com.ftgo.ConsumerService.web;

import lombok.Data;

@Data
public class CreateConsumerResponse {
    private long consumerId;

    public CreateConsumerResponse(long consumerId) {
        this.consumerId = consumerId;
    }
}