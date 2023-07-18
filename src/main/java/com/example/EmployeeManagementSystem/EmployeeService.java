package com.example.EmployeeManagementSystem;

import com.example.EmployeeManagementSystem.Dtos.RequestDtos.EmployeeRequestDto;
import com.example.EmployeeManagementSystem.Dtos.ResponseDtos.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Exceptions.InvalidRequestException;
import com.example.EmployeeManagementSystem.Exceptions.ManagerNotFoundException;
import com.example.EmployeeManagementSystem.Exceptions.NoEmployeeFoundException;
import com.example.EmployeeManagementSystem.Exceptions.ReportingManagerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository ;

    @Autowired
    private JavaMailSender mailSender;

    public String addEmployee(EmployeeRequestDto employeeRequestDto) {

        Optional<Employee> result = employeeRepository.findByName(employeeRequestDto.getReportsTo()) ;

        if(result.isEmpty()){
            throw new ReportingManagerNotFoundException() ;
        }

        Employee employee = Transformer.employeeRequestDtoToEmployee(employeeRequestDto) ;

        // Generate a unique UUID for the employee
        employee.setId(String.valueOf(UUID.randomUUID()));

        // Save the employee to the database
        employeeRepository.save(employee);

        sendMailToUser(employee.getReportsTo(), employee.getEmployeeName(), employee.getPhoneNumber(), employee.getEmail());

        return employee.getId();
    }

    private void sendMailToUser(Employee manager, String employeeName, String phoneNumber, String emailId) {
        String body = "Dear"+manager.getEmployeeName()+",\n\n I hope this email finds you well. \n" +
                "I am writing to inform you that "+employeeName+" will now work under you. \n" +
                "Mobile number is "+phoneNumber+"\n" +
                "Email is "+emailId ;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(body);
        message.setFrom(manager.getEmail());
        message.setTo(emailId);
        message.setSubject("New Employee Added");
        mailSender.send(message);
    }

    public List<EmployeeResponseDto> getAllEmployees() {
        List<Employee> result = employeeRepository.findAll();

        if(result.size() == 0){
            throw new NoEmployeeFoundException() ;
        }

        List<EmployeeResponseDto> employees = new ArrayList<>() ;

        for(Employee i : result){
            EmployeeResponseDto employeeResponseDto = Transformer.employeeToEmployeeResponseDto(i) ;
            employees.add(employeeResponseDto) ;
        }
        return employees ;
    }

    public void deleteEmployee(String id) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isEmpty()){
            throw new NoEmployeeFoundException() ;
        }

        employeeRepository.deleteById(id);
    }

    public void updateEmployee(String id, EmployeeRequestDto updatedEmployee) {

        Optional<Employee> result = employeeRepository.findById(id) ;

        if(result.isEmpty()){
            throw new NoEmployeeFoundException() ;
        }

        Employee employee = Transformer.employeeRequestDtoToEmployee(updatedEmployee) ;
        employee.setId(id);
        employeeRepository.save(employee) ;

    }


                           // Intermediate

    public Employee getNthLevelManager(String employeeId, int level) {

        Optional<Employee> result = employeeRepository.findById(employeeId) ;

        if(result.isEmpty()){
            throw new NoEmployeeFoundException() ;
        }

        Employee employee = result.get() ;

        Employee manager = getNthLevelManagerHelper(employee, level);

        if(manager == null){
            throw new ManagerNotFoundException() ;
        }
        return manager ;
    }

    private Employee getNthLevelManagerHelper(Employee employee, int level) {
        if (level <= 0) {
            return null;
        }
        Employee manager = employee.getReportsTo();

        if (manager == null) {
            return null;
        }

        if (level == 1) {
            return manager;
        }
        return getNthLevelManagerHelper(manager, level - 1);
    }

    public List<Employee> getEmployeesWithPaginationAndSorting(int page, int size, String sort) {
        if (page < 0 || size < 1) {
            throw new InvalidRequestException("Invalid page or size parameter");
        }

        List<String> validSortFields = Arrays.asList("employeeName", "email");
        if (!validSortFields.contains(sort)) {
            throw new InvalidRequestException("Invalid sort field");
        }

        Sort sortOrder = Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        Page<Employee> employeePage =  employeeRepository.findAll(pageable);
        return employeePage.getContent();
    }

}