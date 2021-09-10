package com.ftgo.ConsumerService.web;

import com.ftgo.ConsumerService.ConsumerService;
import com.ftgo.ConsumerService.domain.PersonName;
import com.ftgo.ConsumerService.domain.consumer.entity.Consumer;
import io.eventuate.tram.events.publisher.ResultWithEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {
    private final ConsumerService consumerService;

    public ConsumerController(@Autowired ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/hcheck")
    @ResponseStatus(HttpStatus.OK)
    public HealthCheckResponse healthCheck() {
        return new HealthCheckResponse();
    }

    @PostMapping("/consumers")
    public CreateConsumerResponse create(@RequestBody CreateConsumerRequest request) {
        ResultWithEvents<Consumer> result = consumerService.create(request.getName());
        return new CreateConsumerResponse(result.result.getId());
    }

    @GetMapping("/consumers/{consumerId}")
    public ResponseEntity<GetConsumerResponse> get(@PathVariable long consumerId) {
        return consumerService.findById(consumerId)
                .map(consumer -> new ResponseEntity<>(
                        new GetConsumerResponse(consumerId,
                                PersonName.create(consumer.getName().getFirstName(), consumer.getName().getLastName())),
                                HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}