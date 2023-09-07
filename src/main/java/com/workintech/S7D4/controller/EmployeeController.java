package com.workintech.S7D4.controller;

import com.workintech.S7D4.entity.Employee;
import com.workintech.S7D4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @GetMapping("/byEmail/{email}")
    public Employee findByEmail(@PathVariable String email) {
        return employeeService.findByEmail(email);
    }

    @GetMapping("/byOrder")
    public List<Employee> findAllByOrderLastName() {
        return employeeService.findOrderByLastName();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PostMapping("/bySalary/{salary}")
    public List<Employee> orderBySalary(@PathVariable double salary) {
        return employeeService.findOrderBySalary(salary);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable int id) {
        return employeeService.delete(id);
    }


}
