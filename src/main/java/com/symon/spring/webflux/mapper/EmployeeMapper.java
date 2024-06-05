package com.symon.spring.webflux.mapper;

import com.symon.spring.webflux.dto.EmployeeDto;
import com.symon.spring.webflux.entity.Employee;

public class EmployeeMapper {
    public static  EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
    public static  Employee maptoEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
