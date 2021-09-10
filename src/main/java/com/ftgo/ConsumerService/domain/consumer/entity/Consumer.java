package com.ftgo.ConsumerService.domain.consumer.entity;

import com.ftgo.ConsumerService.domain.Money;
import com.ftgo.ConsumerService.domain.PersonName;
import com.ftgo.ConsumerService.event.ConsumerCreated;
import io.eventuate.tram.events.publisher.ResultWithEvents;
import lombok.Getter;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "consumers")
@Access(AccessType.FIELD)
@Getter
public class Consumer {
    @Id
    @Column(name = "consumer_id")
    private Long id;

    @Embedded
    private PersonNameOnDB name;

    public Consumer() {}

    public Consumer(PersonName name) {
        this.id = Math.abs(new Random().nextLong());
        this.name = name.transformEmbeddable();
    }

    public void validateOrderByConsumer(Money orderTotal) {
        // implement some business logic
    }

    public static ResultWithEvents<Consumer> create(PersonName name) {
        return new ResultWithEvents<>(new Consumer(name), new ConsumerCreated());
    }
}