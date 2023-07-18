package com.example.EmployeeManagementSystem.Exceptions;

public class ManagerNotFoundException extends RuntimeException{

    public ManagerNotFoundException(){
        super("No manager found") ;
    }

}
