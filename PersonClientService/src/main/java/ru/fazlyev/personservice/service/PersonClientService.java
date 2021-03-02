package ru.fazlyev.personservice.service;

import ru.fazlyev.personservice.domain.Person;

public interface PersonClientService {
    Person requestPersonByName(String name);
}
