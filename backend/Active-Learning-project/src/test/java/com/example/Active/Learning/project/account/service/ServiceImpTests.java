package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.repositories.IRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ServiceImpTests {

    //Creating a null new instance of person
     Person person;
     UUID uuid;

    @MockBean
    private final IRepository<Person> mockRepository;

    @Autowired
    ServiceImpl<Person> service;

    public ServiceImpTests(){
        this.mockRepository = mock(IRepository.class);
        this.service = new ServiceImpl<>(this.mockRepository);
        this.person = new Person();
    }

    @BeforeEach
    public void setup(){
        this.person = new Person();
        this.uuid = UUID.randomUUID();
    }

    @Test
    public void findById_returns_entity_when_entity_is_found() {

        //Generate random uuid
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

        when(this.mockRepository.findById(uuid))
                .thenReturn(Optional.empty());

        //Expected results
        ResponseEntity<Person> personResponseEntity = ResponseEntity.notFound().build();

        //Actual results
        ResponseEntity<Person> personService = service.findById(uuid);
        assertEquals(personResponseEntity, personService);
    }
}
