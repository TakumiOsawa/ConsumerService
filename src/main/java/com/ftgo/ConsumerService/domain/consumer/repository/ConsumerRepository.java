package com.ftgo.ConsumerService.domain.consumer.repository;

import com.ftgo.ConsumerService.domain.consumer.entity.Consumer;
import org.springframework.data.repository.CrudRepository;

public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
}
