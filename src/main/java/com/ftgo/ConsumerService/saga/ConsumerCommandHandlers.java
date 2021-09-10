package com.ftgo.ConsumerService.saga;

import com.ftgo.ConsumerService.ConsumerService;
import com.ftgo.ConsumerService.exception.ConsumerVerificationFailedException;
import com.ftgo.ConsumerService.saga.command.ValidateOrderByConsumer;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class ConsumerCommandHandlers {
    private final ConsumerService consumerService;

    public ConsumerCommandHandlers(@Autowired ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    public CommandHandlers commandHandlers() {
        return SagaCommandHandlersBuilder
                .fromChannel("consumerService")
                .onMessage(ValidateOrderByConsumer.class, this::validateOrderForConsumer)
                .build();
    }

    private Message validateOrderForConsumer(CommandMessage<ValidateOrderByConsumer> cm) {
        try {
            consumerService.validateOrderForConsumer(cm.getCommand().getConsumerId(), cm.getCommand().getOrderTotal());
            return withSuccess();
        } catch (ConsumerVerificationFailedException e) {
            return withFailure();
        }
    }
}
