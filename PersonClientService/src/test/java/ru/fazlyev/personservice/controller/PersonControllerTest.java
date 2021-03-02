package ru.fazlyev.personservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import ru.fazlyev.personservice.domain.Person;
import ru.fazlyev.personservice.service.PersonClientServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonClientServiceImpl personService;

    @Test
    void positiveTestGetByNameByStatus() throws Exception {
        when(personService.requestPersonByName("John Doe")).thenReturn(new Person(1L, "John Doe",
                "johndoe@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.get("/persons").param("personName", "John Doe"))
                .andExpect(status().isOk());
    }

    @Test
    void negativeTestGetByNameByStatus() {
        assertThrows(NestedServletException.class, () -> mockMvc.perform(MockMvcRequestBuilders.get("/persons")
                .param("personName", "John Doe")));
    }
}
