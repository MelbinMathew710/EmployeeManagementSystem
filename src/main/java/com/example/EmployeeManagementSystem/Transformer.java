package com.example.EmployeeManagementSystem;

import com.example.EmployeeManagementSystem.Dtos.RequestDtos.EmployeeRequestDto;
import com.example.EmployeeManagementSystem.Dtos.ResponseDtos.EmployeeResponseDto;

public class Transformer {

    public static Employee employeeRequestDtoToEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = Employee.builder()
                .employeeName(employeeRequestDto.getEmployeeName())
                .phoneNumber(employeeRequestDto.getEmployeeName())
                .email(employeeRequestDto.getEmployeeName())
                .reportsTo(employeeRequestDto.getReportsTo())
                .profileImage(employeeRequestDto.getEmployeeName())
                .build();

        return employee ;
    }

    public static EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee){
        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .employeeName(employee.getEmployeeName())
                .phoneNumber(employee.getEmployeeName())
                .email(employee.getEmployeeName())
                .reportsTo(employee.getReportsTo())
                .profileImage(employee.getEmployeeName())
                .build();

        return employeeResponseDto ;
    }

}