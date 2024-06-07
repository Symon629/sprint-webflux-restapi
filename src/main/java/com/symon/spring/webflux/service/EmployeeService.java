package com.symon.spring.webflux.service;

import com.symon.spring.webflux.dto.EmployeeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto);

    Mono<EmployeeDto> getEmployee(String employeeId);
    Flux <EmployeeDto> getAllEmployees();
    Mono<EmployeeDto> updateEmployees(EmployeeDto employeeDto,String employeeId);
    Mono<Void>  deleteEmployeeById(String employeeId);
}
