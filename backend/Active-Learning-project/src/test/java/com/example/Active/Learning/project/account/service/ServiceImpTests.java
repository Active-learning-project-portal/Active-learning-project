package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.repositories.IRepository;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ServiceImpTests {

    //Creating a person class for test
    @Getter
    @Setter
    public static class Person{
        private UUID id;
        private String name;
        private String age;
    }

    //Creating a null new instance of person
    Person person;

    @Mock
    private final IRepository<Person> mockRepository;

    @Autowired
    ServiceImpl<Person> service;

    public ServiceImpTests(){
        this.mockRepository = mock(IRepository.class);
        this.service = new ServiceImpl<>(this.mockRepository);
        this.person = new Person();
    }

    @BeforeEach
    void setup(){
        person = new Person();
    }

    @Test
    public void findById_returns_entity_when_entity_is_found() {
        //Generate random uuid
        UUID uuid = UUID.randomUUID();
        when(this.mockRepository.findById(uuid))
                .thenReturn(Optional.of(this.person));

        //Expected results
        ResponseEntity<Person> personResponseEntity = ResponseEntity.ok().body(this.person);

        //Actual results
        ResponseEntity<Person> personService = service.findById(uuid);

        assertEquals(personResponseEntity, personService);
    }

    @Test
    public void findById_returns_notFoundResponse_when_entity_is_notFound() {
        //Generate rando uuid
        UUID uuid = UUID.randomUUID();
        when(this.mockRepository.findById(uuid))
                .thenReturn(Optional.empty());

        //Expected results
        ResponseEntity<Person> personResponseEntity = ResponseEntity.notFound().build();

        //Actual results
        ResponseEntity<Person> personService = service.findById(uuid);
        assertEquals(personResponseEntity, personService);
    }


    @Test
    public void update_returns_entity_when_entity_is_found() {
        //Generate random uuid
        UUID uuid = UUID.randomUUID();
        when(this.mockRepository.findById(uuid))
                .thenReturn(Optional.of(this.person));


        //Expected results
        ResponseEntity<Person> personResponseEntity = ResponseEntity.ok().body(this.person);

        //Actual results
        ResponseEntity<Person> personService = service.findById(uuid);

        assertEquals(personResponseEntity, personService);
    }

    @Test
    public void update_returns_notFoundResponse_when_entity_is_notFound() {
        //Generate rando uuid
        UUID uuid = UUID.randomUUID();
        when(this.mockRepository.findById(uuid))
                .thenReturn(Optional.empty());

        //Expected results
        ResponseEntity<Person> personResponseEntity = ResponseEntity.notFound().build();

        //Actual results
        ResponseEntity<Person> personService = service.findById(uuid);
        assertEquals(personResponseEntity, personService);
    }
}
