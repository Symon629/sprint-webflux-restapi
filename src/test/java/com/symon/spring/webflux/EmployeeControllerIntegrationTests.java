package com.symon.spring.webflux;


import com.symon.spring.webflux.dto.EmployeeDto;
import com.symon.spring.webflux.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT )
public class EmployeeControllerIntegrationTests {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private WebTestClient webTestClient;

    // this method will execute before any junit testcase.
    @BeforeEach
    public void before(){}



    @Test
    public void testSaveEmployee(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Symon");
        employeeDto.setLastName("Test");
        employeeDto.setEmail("symon.asadul@gmail.com");
        EmployeeDto employeeDto1 = webTestClient.post().uri("/api/employee").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(Mono.just(employeeDto),EmployeeDto.class).exchange().expectStatus().isCreated().expectBody(EmployeeDto.class).consumeWith(System.out::println).returnResult().getResponseBody();
        Assertions.assertEquals(employeeDto.getEmail(),employeeDto1.getEmail());
        //Checks where Employeedid is empty or not.
        Assertions.assertTrue(employeeDto1.getId() != null);
        Assertions.assertNotNull(employeeDto1.getId(), "Employee ID should not be null");

        Assertions.assertFalse(employeeDto1.getId().isEmpty(), "Employee ID should not be empty");
        //YOu could also do this
        //var responseEntity = webTestClient.post().uri("/api/employee").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(Mono.just(employeeDto),EmployeeDto.class).exchange().expectStatus().isCreated().expectBody(EmployeeDto.class).consumeWith(System.out::println).returnResult();
        //responseEntity.getResponseBody().getEmail()

    }
    @Test
    public  void getTestSingleEmployee(){


        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Symon");
        employeeDto.setLastName("Test");
        employeeDto.setEmail("symon.asadul@gmail.com");

        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();
        //Using Collections.singletonMap:
        //Creates a map with a single entry where the key is "id" and the value is savedEmployee.getId().
        //This map is used to replace the {id} placeholder in the URI /api/employee/{id} with the actual ID of the saved employee.
        EmployeeDto employeeDto1 = webTestClient.get().uri("/api/employee/{id}", Collections.singletonMap("id",savedEmployee.getId())).exchange().expectStatus().isOk().expectBody(EmployeeDto.class).consumeWith(System.out::println).returnResult().getResponseBody();
        Assertions.assertEquals(employeeDto.getEmail(),employeeDto1.getEmail());
        //Checks where Employeedid is empty or not.
        Assertions.assertTrue(employeeDto1.getId() != null);
        Assertions.assertNotNull(employeeDto1.getId(), "Employee ID should not be null");

    }
}
