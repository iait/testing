package com.iait.testing.ctrls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iait.testing.entities.EmployeeEntity;
import com.iait.testing.payloads.requests.EmployeeRequest;
import com.iait.testing.payloads.responses.EmployeeResponse;
import com.iait.testing.services.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeCtrl {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> createEmployee(
            @RequestBody EmployeeRequest request) {
        System.out.println("hola");
        EmployeeEntity entityRequest = new EmployeeEntity(request.getName());
        EmployeeEntity entityResponse = employeeService.create(entityRequest);
        EmployeeResponse response = new EmployeeResponse(entityResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> response = employeeService.findAll().stream()
                .map(e -> new EmployeeResponse(e))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
