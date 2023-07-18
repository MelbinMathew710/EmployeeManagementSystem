package com.example.EmployeeManagementSystem.Dtos.ResponseDtos;

import com.example.EmployeeManagementSystem.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private String employeeName;
    private String phoneNumber;
    private String email;
    private Employee reportsTo;
    private String profileImage;
}