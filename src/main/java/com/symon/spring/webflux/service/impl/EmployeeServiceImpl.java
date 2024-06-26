package com.symon.spring.webflux.service.impl;

import com.symon.spring.webflux.dto.EmployeeDto;
import com.symon.spring.webflux.entity.Employee;
import com.symon.spring.webflux.mapper.EmployeeMapper;
import com.symon.spring.webflux.repository.EmployeeRepository;
import com.symon.spring.webflux.service.EmployeeService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service

//We can also use lombok to generate the constructor using allArgsConstructor.

public class EmployeeServiceImpl implements EmployeeService {
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private EmployeeRepository employeeRepository;
    @Override
    public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {
        // connvert EmployeeDto into employee entity
        Employee employee = EmployeeMapper.maptoEmployee(employeeDto);
        Mono<Employee>  savedEmployee = employeeRepository.save(employee);

        // Mono has the map method we can use to convert it into what we like that is an EmployeeDTO
        return  savedEmployee.map((employeeEntity)-> EmployeeMapper.mapToEmployeeDto(employeeEntity));
    }

    @Override
    public Mono<EmployeeDto> getEmployee(String employeeId) {
        Mono<Employee> employeeById = employeeRepository.findById(employeeId);
        return employeeById.map((employeeEntity)->EmployeeMapper.mapToEmployeeDto(employeeEntity));
    }

    @Override
    public Flux<EmployeeDto> getAllEmployees() {
        Flux<Employee> employees = employeeRepository.findAll();
        return employees.map(employeeEntity -> EmployeeMapper.mapToEmployeeDto(employeeEntity)).switchIfEmpty(Flux.empty());
    }


    @Override
    public Mono<EmployeeDto> updateEmployees(EmployeeDto employeeDto,String employeeId) {
        Mono<Employee> employeeMono = employeeRepository.findById(employeeId);
        Mono<Employee> employeeEntity = employeeMono.flatMap((employee)->{
            employee.setEmail(employeeDto.getEmail());
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            return employeeRepository.save(employee);
        });
        return employeeEntity.map((employeetoDto)->EmployeeMapper.mapToEmployeeDto(employeetoDto));
    }

    @Override
    public Mono<Void> deleteEmployeeById(String employeeId) {
        employeeRepository.findById(employeeId)
                .flatMap(employee -> employeeRepository.delete(employee));
        //Or you could just do
        // employeeRepository.deleteById(employeeId);
        return null;
    }

}
