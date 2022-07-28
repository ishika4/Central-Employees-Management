package com.example.management.repository;

import com.example.management.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findByName(String name);

    //Select * from employee where name = "ishika" and domain = "nykaa"
    List<Employee> findByNameAndDomain(String name, String domain);

    //Select * from employee where name LIKE "%ish"
    List<Employee> findByNameContaining(String keyword, Sort sort);
    @Query("FROM Employee WHERE name= :name OR location= :location")
    List<Employee> getEmployeeByNameOrLocation(String name, String location);
    @Transactional
    @Modifying
    @Query("DELETE FROM Employee WHERE name= :name")
    Integer deleteEmployeeByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Employee SET isActive= :isActive WHERE id= :id")
    Integer softDeleteEmployeeByID(Integer isActive, Long id);
}
