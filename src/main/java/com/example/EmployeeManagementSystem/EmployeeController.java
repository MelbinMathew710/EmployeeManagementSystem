package com.example.EmployeeManagementSystem;

import com.example.EmployeeManagementSystem.Dtos.RequestDtos.EmployeeRequestDto;
import com.example.EmployeeManagementSystem.Dtos.ResponseDtos.EmployeeResponseDto;
import com.example.EmployeeManagementSystem.Exceptions.InvalidRequestException;
import com.example.EmployeeManagementSystem.Exceptions.NoEmployeeFoundException;
import com.example.EmployeeManagementSystem.Exceptions.ReportingManagerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add-employee")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {

        try {
            String id = employeeService.addEmployee(employeeRequestDto);
            return new ResponseEntity<>(id, HttpStatus.OK) ;
        }
        catch (ReportingManagerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-employees")
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<EmployeeResponseDto> employees = employeeService.getAllEmployees() ;
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
        catch(NoEmployeeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) {

        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK) ;
        }
        catch (NoEmployeeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeRequestDto updatedEmployee) {

        try {
            employeeService.updateEmployee(id, updatedEmployee) ;
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK) ;
        }
        catch (NoEmployeeFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

                              //Intermediate


        @GetMapping("/get-manager/{employeeId}/{level}")
        public ResponseEntity<?> getNthLevelManager(
                @PathVariable("employeeId") String employeeId,
                @PathVariable("level") int level) {

            try {
                Employee manager = employeeService.getNthLevelManager(employeeId, level);
                return new ResponseEntity<>(manager,HttpStatus.OK) ;
            }
            catch(NoEmployeeFoundException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND) ;
            }
        }

        @GetMapping("/get-employees-with-pagination-and-sorting")
        public ResponseEntity<?> getEmployeesWithPaginationAndSorting(
                @RequestParam(value = "page", defaultValue = "0") int page,
                @RequestParam(value = "size", defaultValue = "10") int size,
                @RequestParam(value = "sort", defaultValue = "employeeName") String sort) {
            try {
                List<Employee> employees = employeeService.getEmployeesWithPaginationAndSorting(page, size, sort);
                return new ResponseEntity<>(employees,HttpStatus.OK);
            } catch (InvalidRequestException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }

}