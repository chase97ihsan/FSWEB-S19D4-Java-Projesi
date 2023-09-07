package com.workintech.S7D4.service;

import com.workintech.S7D4.dao.EmployeeRepository;
import com.workintech.S7D4.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
         this.employeeService=new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void findAll() {
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void findByEmail() {
        String email="ihsan@35";
        assertNull(employeeService.findByEmail(email));

    }

    @Test
    void findOrderByLastName() {
        employeeService.findOrderByLastName();
        verify(employeeRepository).findOrderByLastName();
    }

    @Test
    void saveOne() {
        String email="nazlı@39";
        Employee employee=new Employee();
        employee.setEmail(email);
        given(employeeRepository.findByEmail(email)).willReturn(Optional.of(employee));
        assertNull(employeeService.save(employee));


    }
    @Test
    void saveTwo() {
        String email1="elif@39";
        Employee employee1=new Employee();
        employee1.setEmail(email1);
        given(employeeRepository.findByEmail(email1)).willReturn(Optional.empty());
        given(employeeRepository.save(employee1)).willReturn(employee1);
        Employee employee2=employeeService.save(employee1);
        assertEquals("elif @39",employee2.getEmail());

    }

    @Test
    void findOrderBySalary() {
    }

    @Test
    void delete() {
    }
}