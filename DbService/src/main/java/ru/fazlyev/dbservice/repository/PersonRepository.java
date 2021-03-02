package ru.fazlyev.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fazlyev.dbservice.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);
}
