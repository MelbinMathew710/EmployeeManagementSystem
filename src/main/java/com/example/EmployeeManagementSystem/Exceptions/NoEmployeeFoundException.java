package com.example.EmployeeManagementSystem.Exceptions;

public class NoEmployeeFoundException extends RuntimeException{

    public NoEmployeeFoundException(){
        super("No employee found") ;
    }

}
