package ru.fazlyev.personservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fazlyev.personservice.service.PersonClientService;

@Controller
public class PersonController {
    private final PersonClientService personService;

    public PersonController(PersonClientService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public String getByName(@RequestParam String personName, Model model) {
        model.addAttribute("person", personService.requestPersonByName(personName));
        return "person";
    }
}
