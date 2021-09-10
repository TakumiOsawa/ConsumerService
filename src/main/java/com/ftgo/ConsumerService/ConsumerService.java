package com.ftgo.ConsumerService;

import com.ftgo.ConsumerService.domain.Money;
import com.ftgo.ConsumerService.domain.PersonName;
import com.ftgo.ConsumerService.domain.consumer.entity.Consumer;
import com.ftgo.ConsumerService.domain.consumer.repository.ConsumerRepository;
import com.ftgo.ConsumerService.event.ConsumerDomainEventPublisher;
import com.ftgo.ConsumerService.exception.ConsumerNotFoundException;
import io.eventuate.tram.events.publisher.ResultWithEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class ConsumerService {
    private final ConsumerRepository consumerRepository;
    private final ConsumerDomainEventPublisher domainEventPublisher;

    public ConsumerService(@Autowired ConsumerRepository consumerRepository,
                           @Autowired ConsumerDomainEventPublisher domainEventPublisher) {
        this.consumerRepository = consumerRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void validateOrderForConsumer(long consumerId, Money orderTotal) {
        Optional<Consumer> consumer = consumerRepository.findById(consumerId);
        if (consumer.isPresent()){
            consumer.get().validateOrderByConsumer(orderTotal);
        }
        else{
            throw new ConsumerNotFoundException(consumerId);
        }
    }

    @Transactional
    public ResultWithEvents<Consumer> create(PersonName name) {
        ResultWithEvents<Consumer> rwe = Consumer.create(name);
        consumerRepository.save(rwe.result);
        domainEventPublisher.publish(rwe.result, rwe.events);
        return rwe;
    }

    public Optional<Consumer> findById(long consumerId) {
        return consumerRepository.findById(consumerId);
    }
}