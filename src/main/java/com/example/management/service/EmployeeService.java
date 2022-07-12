package com.example.management.service;

import com.example.management.dtos.requests.EmployeeAddRequestDTO;
import com.example.management.dtos.requests.EmployeeUpdateRequestDTO;
import com.example.management.dtos.responses.EmployeesReadResponseDTO;
import com.example.management.exception.EmployeeException;
import com.example.management.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeAddRequestDTO> getEmployees(int pageNumber, int pageSize);
    String saveEmployee(EmployeeAddRequestDTO employee);
    EmployeeAddRequestDTO getSingleEmployee(Long id) throws EmployeeException;
    void deleteEmployee(Long id);
    String updateEmployee(Long id, EmployeeUpdateRequestDTO employeeUpdateRequestDTO);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeesByNameAndDomain(String name, String domain);
    List<Employee> getEmployeesByKeyword(String name);
    List<Employee> getEmployeeByNameOrLocation(String name, String location);
    Integer deleteEmployeeByName(String name);
}
