package com.workintech.S7D4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.S7D4.entity.Employee;
import com.workintech.S7D4.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Test
    void findAllByOrderLastName() throws  Exception {
        Employee employee=new Employee();
        employee.setFirstName("Ihsan");
        List<Employee> employees=new ArrayList<>();
        employees.add(employee);
        when(employeeService.findOrderByLastName()).thenReturn(employees);
        mockMvc.perform(get("/employees/byOrder"))
                .andExpect(status().isOk());
        verify(employeeService).findOrderByLastName();
    }
    @Test
    void save() throws Exception{
        Employee employee=new Employee();
        employee.setFirstName("Ihsan");
        employee.setLastName("Dogan");
        employee.setEmail("ihsan@19");
        when(employeeService.save(employee)).thenReturn(employee);
        mockMvc.perform(post("/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Dogan"));

        verify(employeeService).save(employee);
    }
    public static  String asJsonString(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);

        }catch (Exception ex){
         throw new RuntimeException(ex);
        }
    }
}