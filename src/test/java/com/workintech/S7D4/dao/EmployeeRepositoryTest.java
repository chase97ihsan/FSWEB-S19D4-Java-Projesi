package com.workintech.S7D4.dao;

import com.workintech.S7D4.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach
    void setUp() {
        Employee employee1=new Employee();
        employee1.setFirstName("İhsan");
        employee1.setLastName("Dogan");
        employee1.setEmail("ihsan97@com");
        employee1.setSalary(27000);

        Employee employee2=new Employee();
        employee2.setFirstName("Mehmet");
        employee2.setLastName("Al");
        employee2.setEmail("mehmet96@com");
        employee2.setSalary(25000);

        Employee employee3=new Employee();
        employee3.setFirstName("Necati");
        employee3.setLastName("Kel");
        employee3.setEmail("necati95@com");
        employee3.setSalary(35000);
        List<Employee> employees=new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employeeRepository.saveAll(employees);
    }

    @AfterEach
    void tearDown() {
    employeeRepository.deleteAll();
    }
    @Test
    void findByEmail() {
        String email="zeynep@24";
        Optional<Employee> optional=employeeRepository.findByEmail(email);
        assertEquals(Optional.empty(),optional);

        String email2="ihsan97@com";
        Optional<Employee> optional2=employeeRepository.findByEmail(email2);
        assertNotNull(optional2.get());
        assertEquals("İhsan",optional2.get().getFirstName());
        assertEquals(27000,optional2.get().getSalary());
    }

    @Test
    void findOrderBySalary() {
        List<Employee> employees=employeeRepository.findOrderBySalary(25000);
        assertEquals(2,employees.size());
        assertEquals("Necati",employees.get(0).getFirstName());
        assertEquals("Dogan",employees.get(1).getLastName());
    }

    @Test
    void findOrderByLastName() {
        List<Employee> employees=employeeRepository.findOrderByLastName();
        assertEquals(3,employees.size());
        assertEquals("Mehmet",employees.get(0).getFirstName());
        assertEquals("Kel",employees.get(2).getLastName());
    }
}