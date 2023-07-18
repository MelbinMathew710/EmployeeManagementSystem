package com.example.EmployeeManagementSystem.Exceptions;

public class ReportingManagerNotFoundException extends RuntimeException{

    public ReportingManagerNotFoundException(){
        super("Reporting Manager not found") ;
    }

}
