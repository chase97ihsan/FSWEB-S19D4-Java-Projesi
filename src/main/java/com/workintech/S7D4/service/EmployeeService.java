package com.workintech.S7D4.service;

import com.workintech.S7D4.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee findByEmail(String email);

    List<Employee> findOrderByLastName();

    Employee save(Employee employee);

    List<Employee> findOrderBySalary(double salary);

    Employee delete(int id);
}
