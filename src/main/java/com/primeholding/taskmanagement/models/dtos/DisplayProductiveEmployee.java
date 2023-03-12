package com.primeholding.taskmanagement.models.dtos;

public class DisplayProductiveEmployee {

    String fullName;

    Integer numberOfTasksCompletedPastMonth;

    public DisplayProductiveEmployee() {
    }

    public DisplayProductiveEmployee(String fullName, Integer numberOfTasksCompletedPastMonth) {
        this.fullName = fullName;
        this.numberOfTasksCompletedPastMonth = numberOfTasksCompletedPastMonth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getNumberOfTasksCompletedPastMonth() {
        return numberOfTasksCompletedPastMonth;
    }

    public void setNumberOfTasksCompletedPastMonth(Integer numberOfTasksCompletedPastMonth) {
        this.numberOfTasksCompletedPastMonth = numberOfTasksCompletedPastMonth;
    }
}
