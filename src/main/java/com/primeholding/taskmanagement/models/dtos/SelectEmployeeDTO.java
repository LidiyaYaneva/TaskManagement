package com.primeholding.taskmanagement.models.dtos;

public class SelectEmployeeDTO {

    private Long id;

    private String fullName;

    public SelectEmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
