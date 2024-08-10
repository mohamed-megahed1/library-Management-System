package com.mohamedahmed.libraryManagementSystem.mapper;

import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;
import com.mohamedahmed.libraryManagementSystem.entities.Patron;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PatronMapperTest {

    @Autowired
    private PatronMapper patronMapper;

    static Patron patron = new Patron();
    static Patron patron2 = new Patron();
    static PatronDto patronDto = new PatronDto();
    static List<Patron> patrons = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        patron.setId(1L);
        patron.setUsername("patron1");
        patron.setFirstName("First1");
        patron.setLastName("Last1");
        patron.setPassword("password123");
        patron.setEmail("patron1@example.com");
        patron.setPhoneNumber("+1234567890");
        patron.setRole("USER");

        patron2.setId(2L);
        patron2.setUsername("patron2");
        patron2.setFirstName("First2");
        patron2.setLastName("Last2");
        patron2.setPassword("password456");
        patron2.setEmail("patron2@example.com");
        patron2.setPhoneNumber("+0987654321");
        patron2.setRole("USER");

        patrons = Arrays.asList(patron, patron2);

        patronDto.setId(1L);
        patronDto.setUsername("patron1");
        patronDto.setFirstName("First1");
        patronDto.setLastName("Last1");
        patronDto.setPassword("password123");
        patronDto.setEmail("patron1@example.com");
        patronDto.setPhoneNumber("+1234567890");
        patronDto.setRole("USER");
    }

    @Test
    void testFromPatronToPatronDto() {
        PatronDto dto = patronMapper.fromPatronToPatronDto(patron);
        assertEquals(patron.getId(), dto.getId());
        assertEquals(patron.getUsername(), dto.getUsername());
        assertEquals(patron.getFirstName(), dto.getFirstName());
        assertEquals(patron.getLastName(), dto.getLastName());
        assertEquals(patron.getPassword(), dto.getPassword());
        assertEquals(patron.getEmail(), dto.getEmail());
        assertEquals(patron.getPhoneNumber(), dto.getPhoneNumber());
        assertEquals(patron.getRole(), dto.getRole());
    }

    @Test
    void testFromPatronDtoToPatron() {
        Patron entity = patronMapper.fromPatronDtoToPatron(patronDto);
        assertEquals(patronDto.getId(), entity.getId());
        assertEquals(patronDto.getUsername(), entity.getUsername());
        assertEquals(patronDto.getFirstName(), entity.getFirstName());
        assertEquals(patronDto.getLastName(), entity.getLastName());
        assertEquals(patronDto.getPassword(), entity.getPassword());
        assertEquals(patronDto.getEmail(), entity.getEmail());
        assertEquals(patronDto.getPhoneNumber(), entity.getPhoneNumber());
        assertEquals(patronDto.getRole(), entity.getRole());
    }

    @Test
    void testFromPatronToPatronDtoList() {
        List<PatronDto> dtos = patronMapper.fromPatronToPatronDto(patrons);
        assertEquals(patrons.size(), dtos.size());

        assertEquals(patron.getId(), dtos.get(0).getId());
        assertEquals(patron.getUsername(), dtos.get(0).getUsername());
        assertEquals(patron.getFirstName(), dtos.get(0).getFirstName());
        assertEquals(patron.getLastName(), dtos.get(0).getLastName());
        assertEquals(patron.getPassword(), dtos.get(0).getPassword());
        assertEquals(patron.getEmail(), dtos.get(0).getEmail());
        assertEquals(patron.getPhoneNumber(), dtos.get(0).getPhoneNumber());
        assertEquals(patron.getRole(), dtos.get(0).getRole());

        assertEquals(patron2.getId(), dtos.get(1).getId());
        assertEquals(patron2.getUsername(), dtos.get(1).getUsername());
        assertEquals(patron2.getFirstName(), dtos.get(1).getFirstName());
        assertEquals(patron2.getLastName(), dtos.get(1).getLastName());
        assertEquals(patron2.getPassword(), dtos.get(1).getPassword());
        assertEquals(patron2.getEmail(), dtos.get(1).getEmail());
        assertEquals(patron2.getPhoneNumber(), dtos.get(1).getPhoneNumber());
        assertEquals(patron2.getRole(), dtos.get(1).getRole());
    }

}
