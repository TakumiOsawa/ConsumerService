package com.ftgo.ConsumerService.web;

import com.ftgo.ConsumerService.domain.PersonName;
import lombok.Getter;

@Getter
public class GetConsumerResponse {
    private long consumerId;
    private PersonName name;

    public GetConsumerResponse(long consumerId, PersonName name) {
        this.consumerId = consumerId;
        this.name = name;
    }
}