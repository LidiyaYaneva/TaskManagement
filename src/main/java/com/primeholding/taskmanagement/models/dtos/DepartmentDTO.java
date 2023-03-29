package com.primeholding.taskmanagement.models.dtos;

public class DepartmentDTO {

    private String name;

    private Integer numberOfTasksCompletedPastMonth;

    public DepartmentDTO(String name, int numberOfTasksCompletedPastMonth) {
        this.name = name;
        this.numberOfTasksCompletedPastMonth = numberOfTasksCompletedPastMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfTasksCompletedPastMonth() {
        return numberOfTasksCompletedPastMonth;
    }

    public void setNumberOfTasksCompletedPastMonth(Integer numberOfTasksCompletedPastMonth) {
        this.numberOfTasksCompletedPastMonth = numberOfTasksCompletedPastMonth;
    }
}
