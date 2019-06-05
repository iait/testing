package com.iait.testing.services;

import java.util.List;

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

    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity create(EmployeeEntity entity) {
        return employeeRepository.save(entity);
    }
}
