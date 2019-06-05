package com.iait.testing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iait.testing.entities.EmployeeEntity;
import com.iait.testing.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity findByName(String name) {
        return employeeRepository.findByName(name);
    }

}
