package com.ftgo.ConsumerService.config;

import com.ftgo.ConsumerService.ConsumerService;
import com.ftgo.ConsumerService.domain.consumer.repository.ConsumerRepository;
import com.ftgo.ConsumerService.event.ConsumerDomainEventPublisher;
import com.ftgo.ConsumerService.saga.ConsumerCommandHandlers;
import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.common.NoopDuplicateMessageDetector;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TramEventsPublisherConfiguration.class,
        TramMessageProducerJdbcConfiguration.class,
        EventuateTramKafkaMessageConsumerConfiguration.class,
        SagaParticipantConfiguration.class })
public class ConsumerServiceConfiguration {
    @Bean
    public ConsumerService orderService(ConsumerRepository consumerRepository,
                                        ConsumerDomainEventPublisher eventPublisher) {
        return new ConsumerService(consumerRepository, eventPublisher);
    }

    @Bean
    public ConsumerDomainEventPublisher orderAggregateEventPublisher(DomainEventPublisher eventPublisher) {
        return new ConsumerDomainEventPublisher(eventPublisher);
    }

    @Bean
    public DuplicateMessageDetector duplicateMessageDetector() {
        return new NoopDuplicateMessageDetector();
    }

    @Bean
    public ConsumerCommandHandlers consumerCommandHandlers(ConsumerService consumerService) {
        return new ConsumerCommandHandlers(consumerService);
    }

    @Bean
    public SagaCommandDispatcher consumerCommandHandlersDispatcher(ConsumerCommandHandlers consumerCommandHandlers,
                                                                SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
        return sagaCommandDispatcherFactory.make("consumerService",
                consumerCommandHandlers.commandHandlers());
    }
}