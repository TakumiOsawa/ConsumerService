package com.ftgo.ConsumerService.domain;

import com.ftgo.ConsumerService.domain.consumer.entity.PersonNameOnDB;
import lombok.Getter;

@Getter
public class PersonName {
    private String firstName;
    private String lastName;

    private PersonName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static PersonName create(String firstName, String lastName) {
        return new PersonName(firstName, lastName);
    }

    public PersonNameOnDB transformEmbeddable() {
        return new PersonNameOnDB(firstName, lastName);
    }
}