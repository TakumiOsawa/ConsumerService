package com.ftgo.ConsumerService.domain.consumer.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class PersonNameOnDB {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public PersonNameOnDB() {}

    public PersonNameOnDB(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
