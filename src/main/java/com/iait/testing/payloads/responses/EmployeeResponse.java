package com.iait.testing.payloads.responses;

import com.iait.testing.entities.EmployeeEntity;

public class EmployeeResponse {

    private Long id;
    private String name;

    public EmployeeResponse(EmployeeEntity entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
