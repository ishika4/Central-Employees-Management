package com.example.management.controller;

import com.example.management.model.Employee;
import com.example.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/healthCheck")
    public String get(){
        return "ishika";
    }

    //@RequestMapping(value = "/employees", method = RequestMethod.GET)
    @GetMapping("/employees")
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(employeeService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeService.getSingleEmployee(id),HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        employee.setId(id);
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<String> deleteEmployees(@RequestParam Long id){
        employeeService.deleteEmployee((id));
        return new ResponseEntity<>("Employee deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/employees/filterByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
        return new ResponseEntity<>(employeeService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByNameAndDomain")
    public ResponseEntity<List<Employee>> getEmployeeByNameAndDomain(@RequestParam String name, @RequestParam String domain){
        return new ResponseEntity<>(employeeService.getEmployeesByNameAndDomain(name, domain), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByKeyword")
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name){
        return new ResponseEntity<>(employeeService.getEmployeesByKeyword(name), HttpStatus.OK);
    }

    @GetMapping("/employees/{name}/{location}")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@PathVariable String name,@PathVariable String location){
        return new ResponseEntity<>(employeeService.getEmployeeByNameOrLocation(name, location), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{name}")
    public ResponseEntity<String> deleteEmployeesByName(@PathVariable String name){
        return new ResponseEntity<>( employeeService.deleteEmployeeByName(name) +" No of records affected", HttpStatus.OK);
    }


}

