package com.ftgo.ConsumerService.event;

import com.ftgo.ConsumerService.domain.consumer.entity.Consumer;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

public class ConsumerDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Consumer> {
    public ConsumerDomainEventPublisher(DomainEventPublisher eventPublisher) {
        super(eventPublisher, Consumer.class, Consumer::getId);
    }
}