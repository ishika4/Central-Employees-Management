package com.example.management.controller;


import com.example.management.dtos.requests.EmployeeAddRequestDTO;
import com.example.management.dtos.requests.EmployeeUpdateRequestDTO;
import com.example.management.dtos.responses.EmployeesReadResponseDTO;
import com.example.management.exception.EmployeeException;
import com.example.management.model.Employee;
import com.example.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/healthCheck")
    public String get(){
        return "Application server is up!";
    }

    //@RequestMapping(value = "/employees", method = RequestMethod.GET)
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<EmployeeAddRequestDTO>> getEmployees(@RequestParam (defaultValue = "0") @Min(value = 0,message = "Page Number must be greater than or equal to 0") Integer pageNumber, @RequestParam @Min(value = 1,message = "Page Size must be greater than or equal to 1") Integer pageSize) throws EmployeeException {
            return new ResponseEntity<>(employeeService.getEmployees(pageNumber, pageSize), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAddRequestDTO> getEmployee(@PathVariable("id") @Min(value = 0, message = "Employee id should be positive") @Valid Long id) throws EmployeeException {
        return new ResponseEntity<>(employeeService.getSingleEmployee(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveEmployee(@RequestBody @Valid EmployeeAddRequestDTO employeeAddRequestDTO){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeAddRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO) throws EmployeeException {
        return new ResponseEntity<>(employeeService.updateEmployee(id,employeeUpdateRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployees(@RequestParam Long id){
        employeeService.deleteEmployee((id));
        return new ResponseEntity<>("Employee deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/filterByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
        return new ResponseEntity<>(employeeService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/filterByNameAndDomain")
    public ResponseEntity<List<Employee>> getEmployeeByNameAndDomain(@RequestParam String name, @RequestParam String domain){
        return new ResponseEntity<>(employeeService.getEmployeesByNameAndDomain(name, domain), HttpStatus.OK);
    }

    @GetMapping("/filterByKeyword")
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name){
        return new ResponseEntity<>(employeeService.getEmployeesByKeyword(name), HttpStatus.OK);
    }

    @GetMapping("/{name}/{location}")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@PathVariable String name,@PathVariable String location){
        return new ResponseEntity<>(employeeService.getEmployeeByNameOrLocation(name, location), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteEmployeesByName(@PathVariable String name){
        return new ResponseEntity<>( employeeService.deleteEmployeeByName(name) +" No of records affected", HttpStatus.OK);
    }


}

