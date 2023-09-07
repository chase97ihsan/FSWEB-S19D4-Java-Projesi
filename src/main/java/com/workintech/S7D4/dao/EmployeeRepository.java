package com.workintech.S7D4.dao;

import com.workintech.S7D4.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmail(String email);
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary ORDER BY e.salary desc")
    List<Employee> findOrderBySalary(double salary);
    @Query("SELECT e FROM Employee e  ORDER BY e.lastName")
    List<Employee> findOrderByLastName();
}
