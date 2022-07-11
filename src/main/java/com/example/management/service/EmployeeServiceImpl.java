package com.example.management.service;

import com.example.management.model.Employee;
import com.example.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees(int pageNumber, int pageSize) {
        // can sort for multiple columns too
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id", "domain");
        return employeeRepository.findAll(pages).getContent();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent())
            return employee.get();
        throw new RuntimeException("Employee is not found for the id"+id);
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent())
            employeeRepository.deleteById(id);
        else
            throw new RuntimeException("Employee is not found for the id "+id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        List<Employee> employee = employeeRepository.findByName(name);
        if(employee.isEmpty())
            throw new RuntimeException("Employees are not found for the name "+name);
        return employee;
    }

    @Override
    public List<Employee> getEmployeesByNameAndDomain(String name, String domain) {
        return employeeRepository.findByNameAndDomain(name, domain);
    }

    @Override
    public List<Employee> getEmployeesByKeyword(String name) {
        // adding sorting
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return employeeRepository.findByNameContaining(name, sort);
    }

    @Override
    public List<Employee> getEmployeeByNameOrLocation(String name, String location) {
        return employeeRepository.getEmployeeByNameOrLocation(name, location);
    }

    @Override
    public Integer deleteEmployeeByName(String name) {
        return employeeRepository.deleteEmployeeByName(name);
    }
}
