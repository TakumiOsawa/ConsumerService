CREATE DATABASE IF NOT EXISTS consumer;

USE consumer;

DROP TABLE IF EXISTS consumers;

CREATE TABLE consumers
(
    consumer_id bigint NOT NULL,
    first_name varchar(16) NOT NULL,
    last_name varchar(16) NOT NULL,
    PRIMARY KEY (consumer_id)
);