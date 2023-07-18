package com.example.EmployeeManagementSystem.Dtos.RequestDtos;

import com.example.EmployeeManagementSystem.Employee;
import lombok.Data;

@Data
public class EmployeeRequestDto {
    private String employeeName;
    private String phoneNumber;
    private String email;
    private Employee reportsTo;
    private String profileImage;
}