package ru.fazlyev.dbservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fazlyev.dbservice.domain.Person;
import ru.fazlyev.dbservice.dto.PersonDto;
import ru.fazlyev.dbservice.repository.PersonRepository;

@Service
public class DbServiceImpl implements DbService {
    private final PersonRepository personRepository;

    public DbServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RabbitListener(queues = "person-queue")
    @Transactional(readOnly = true)
    @Override
    public Person responseWithName(PersonDto personDto) {
        final Person person = personRepository.findByName(personDto.getName());

        return Person.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .build();
    }
}
