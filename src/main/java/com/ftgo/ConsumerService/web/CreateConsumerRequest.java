package com.ftgo.ConsumerService.web;

import com.ftgo.ConsumerService.domain.PersonName;
import lombok.Data;

@Data
public class CreateConsumerRequest {
    private PersonName name;

    public CreateConsumerRequest(PersonName name) {
        this.name = name;
    }
}