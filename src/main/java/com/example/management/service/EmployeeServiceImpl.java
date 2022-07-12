package com.example.management.service;

import com.example.management.dtos.requests.EmployeeAddRequestDTO;
import com.example.management.dtos.requests.EmployeeUpdateRequestDTO;
import com.example.management.dtos.responses.EmployeesReadResponseDTO;
import com.example.management.enums.EmployeeExceptionEnum;
import com.example.management.exception.EmployeeException;
import com.example.management.model.Employee;
import com.example.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeAddRequestDTO> getEmployees(int pageNumber, int pageSize) throws EmployeeException{
        // can sort for multiple columns too
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id", "domain");
        List<Employee> employees = employeeRepository.findAll(pages).getContent();
        if(employees.isEmpty())
            throw new EmployeeException(EmployeeExceptionEnum.USER_NOT_FOUND);

        EmployeesReadResponseDTO employeesReadResponseDTO = new EmployeesReadResponseDTO();

        List<EmployeeAddRequestDTO> list = new ArrayList<>();

        for(Employee employee : employees){
            EmployeeAddRequestDTO employeeAddRequestDTO = new EmployeeAddRequestDTO();
            employeeAddRequestDTO.setName(employee.getName());
            employeeAddRequestDTO.setEmail(employee.getEmail());
            employeeAddRequestDTO.setSalary(employee.getSalary());
            employeeAddRequestDTO.setLocation(employee.getLocation());
            employeeAddRequestDTO.setAge(employee.getAge());
            employeeAddRequestDTO.setDomain(employee.getDomain());
            employeeAddRequestDTO.setId(employee.getId());
            employeeAddRequestDTO.setIsActive(employee.getIsActive());

            list.add(employeeAddRequestDTO);
        }

        employeesReadResponseDTO.setEmployees(list);
        return employeesReadResponseDTO.getEmployees();
    }

    @Override
    public String saveEmployee(EmployeeAddRequestDTO employeeAddRequestDTO) {
        Employee employee = new Employee();
        //employee.setId(employeeAddRequestDTO.getId());
        employee.setName(employeeAddRequestDTO.getName());
        employee.setDomain(employeeAddRequestDTO.getDomain());
        employee.setLocation(employeeAddRequestDTO.getLocation());
        employee.setAge(employeeAddRequestDTO.getAge());
        employee.setEmail(employeeAddRequestDTO.getEmail());
        employee.setSalary(employeeAddRequestDTO.getSalary());
        employee.setIsActive(1);

        Employee savedEmployee = employeeRepository.save(employee);

        return "Employee Added Successfully";
    }

    @Override
    public EmployeeAddRequestDTO getSingleEmployee(Long id) throws EmployeeException{
        Optional<Employee> optional = employeeRepository.findById(id);
        EmployeeAddRequestDTO employeeAddRequestDTO = new EmployeeAddRequestDTO();
        if(optional.isPresent())
            {
                Employee employee = optional.get();
                employeeAddRequestDTO.setName(employee.getName());
                employeeAddRequestDTO.setEmail(employee.getEmail());
                employeeAddRequestDTO.setSalary(employee.getSalary());
                employeeAddRequestDTO.setLocation(employee.getLocation());
                employeeAddRequestDTO.setAge(employee.getAge());
                employeeAddRequestDTO.setDomain(employee.getDomain());
                employeeAddRequestDTO.setId(employee.getId());
                employeeAddRequestDTO.setIsActive(employee.getIsActive());
            }
        else {
            throw new EmployeeException(EmployeeExceptionEnum.USER_NOT_FOUND);
        }

        return employeeAddRequestDTO;
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Integer isActive = 0;
        if(employee.isPresent())
            employeeRepository.softDeleteEmployeeByID(isActive, id);
        else
            throw new EmployeeException(EmployeeExceptionEnum.USER_NOT_FOUND);
    }

    @Override
    public String updateEmployee(Long id, EmployeeUpdateRequestDTO employeeUpdateRequestDTO) throws EmployeeException {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()) {
            employee = optional.get();
            employee.setName(employeeUpdateRequestDTO.getName());
            employee.setLocation(employeeUpdateRequestDTO.getLocation());
            employee.setAge(employeeUpdateRequestDTO.getAge());
            employee.setEmail(employeeUpdateRequestDTO.getEmail());
            employee.setSalary(employeeUpdateRequestDTO.getSalary());
        }
        else {
            throw new EmployeeException(EmployeeExceptionEnum.USER_NOT_FOUND);
        }
        employeeRepository.save(employee);
        return "Employee Updated Successfully";
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
