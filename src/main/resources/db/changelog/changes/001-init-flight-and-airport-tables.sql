--liquibase formatted sql

--changeset miks:1
CREATE TABLE airports
(
    airport VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    PRIMARY KEY (airport)
);

--changeset miks:2
CREATE TABLE flights
(
    id             BIGINT NOT NULL,
    airport_from   VARCHAR(255),
    airport_to     VARCHAR(255),
    carrier        VARCHAR(255),
    departure_time TIMESTAMP,
    arrival_time   TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT flight_airport_from_fkey FOREIGN KEY (airport_from) REFERENCES airports,
    CONSTRAINT flight_airport_to_fkey FOREIGN KEY (airport_to) REFERENCES airports,
    CONSTRAINT flights_should_be_unique UNIQUE (airport_from, airport_to, carrier, departure_time, arrival_time)
);

--changeset miks:3
CREATE SEQUENCE flight_id_sequence START WITH 1 INCREMENT BY 1;
