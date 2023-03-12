//package com.example.Active.Learning.project.account.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.*;
//
//public class ServiceImpTests {
//
//    //Creating a null new instance of person
//     Person person;
//     UUID uuid;
//
//    @MockBean
//    private final IRepository<Person> mockRepository;
//
//    @Autowired
//    ServiceImpl<Person> service;
//
//    public ServiceImpTests(){
//        this.mockRepository = mock(IRepository.class);
//        this.service = new ServiceImpl<>(this.mockRepository);
//        this.person = new Person();
//    }
//
//    @BeforeEach
//    public void setup(){
//        this.person = new Person();
//        this.uuid = UUID.randomUUID();
//    }
//
//    @Test
//    public void findById_returns_entity_when_entity_is_found() {
//
//        //Generate random uuid
//        when(this.mockRepository.findById(uuid))
//                .thenReturn(Optional.of(this.person));
//
//        //Expected results
//        Person personResponseEntity = this.person;
//
//        //Actual results
//        Person personService = service.findById(uuid);
//
//        assertEquals(personResponseEntity, personService);
//    }
//
//    @Test
//    public void findById_returns_notFoundResponse_when_entity_is_notFound() {
//        when(this.mockRepository.findById(uuid))
//                .thenReturn(Optional.empty());
//
//        //Actual results
//        Person personService = service.findById(uuid);
//        assertNull(personService);
//    }
//
//
//    @Test
//    public void update_returns_entity_when_entity_is_found() {
//        when(this.mockRepository.findById(uuid))
//                .thenReturn(Optional.of(this.person));
//
//        //Expected results
//        Person person = this.person;
//
//        //Actual results
//        Person personService = service.findById(uuid);
//
//        assertEquals(person, personService);
//    }
//
//    @Test
//    public void update_returns_notFoundResponse_when_entity_is_notFound() {
//
//        when(this.mockRepository.findById(uuid))
//                .thenReturn(Optional.empty());
//
//        //Actual results
//        Person personService = service.findById(uuid);
//        assertNull(personService);
//    }
//}
