package com.ftgo.ConsumerService.exception;

public class ConsumerNotFoundException extends RuntimeException {
    public ConsumerNotFoundException(Long consumerId) {
        super(consumerId.toString());
    }
}