package com.workintech.S7D4.service;

import com.workintech.S7D4.dao.EmployeeRepository;
import com.workintech.S7D4.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();

    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> optional = employeeRepository.findByEmail(email);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Employee> findOrderByLastName() {
        return employeeRepository.findOrderByLastName();
    }

    @Override
    public Employee save(Employee employee) {
        Employee employee1 = findByEmail(employee.getEmail());
        if (employee1 == null) {
            return employeeRepository.save(employee);
        }
        return null;

    }

    @Override
    public List<Employee> findOrderBySalary(double salary) {
        return employeeRepository.findOrderBySalary(salary);
    }

    @Override
    public Employee delete(int id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
