package com.iait.testing.ctrls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iait.testing.payloads.EmployeeResponse;
import com.iait.testing.services.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeCtrl {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> response = employeeService.findAll().stream()
                .map(e -> new EmployeeResponse(e))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
