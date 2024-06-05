package com.symon.spring.webflux.controller;

import com.symon.spring.webflux.dto.EmployeeDto;
import com.symon.spring.webflux.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;
    //Build reactive save employee rest api
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.saveEmployee(employeeDto);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<EmployeeDto> getEmployee(@PathVariable("id") String employeeId){
        return employeeService.getEmployee(employeeId);
    }
    @GetMapping("/all")
    public Flux<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
