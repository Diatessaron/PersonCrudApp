package ru.fazlyev.dbservice.service;

import ru.fazlyev.dbservice.domain.Person;
import ru.fazlyev.dbservice.dto.PersonDto;

public interface DbService {
    Person responseWithName(PersonDto personDto);
}
