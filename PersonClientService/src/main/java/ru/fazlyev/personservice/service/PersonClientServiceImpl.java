package ru.fazlyev.personservice.service;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import ru.fazlyev.personservice.domain.Person;
import ru.fazlyev.personservice.dto.PersonDto;

@Service
public class PersonClientServiceImpl implements PersonClientService {
    private final RabbitTemplate template;
    private final DirectExchange directExchange;
    public static final String ROUTING_KEY = "person";

    public PersonClientServiceImpl(RabbitTemplate template, DirectExchange directExchange) {
        this.template = template;
        this.directExchange = directExchange;
    }

    public Person requestPersonByName(String name) {
        PersonDto personDto = PersonDto.builder()
                .name(name)
                .build();

        return template.convertSendAndReceiveAsType(
                directExchange.getName(),
                ROUTING_KEY,
                personDto,
                new ParameterizedTypeReference<>() {
                });
    }
}
