package com.example.management.service;

import com.example.management.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees(int pageNumber, int pageSize);
    Employee saveEmployee(Employee employee);
    Employee getSingleEmployee(Long id);
    void deleteEmployee(Long id);
    Employee updateEmployee(Employee employee);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeesByNameAndDomain(String name, String domain);
    List<Employee> getEmployeesByKeyword(String name);
    List<Employee> getEmployeeByNameOrLocation(String name, String location);
    Integer deleteEmployeeByName(String name);
}
