package com.primeholding.taskmanagement.models.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DisplayEmployeeWithStatsDTO {

    private Long id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private BigDecimal monthlySalary;

    private Long tasksCompletedCount;

    private Long tasksInProgressCount;

    public DisplayEmployeeWithStatsDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Long getTasksCompletedCount() {
        return tasksCompletedCount;
    }

    public void setTasksCompletedCount(Long tasksCompletedCount) {
        this.tasksCompletedCount = tasksCompletedCount;
    }

    public Long getTasksInProgressCount() {
        return tasksInProgressCount;
    }

    public void setTasksInProgressCount(Long tasksInProgressCount) {
        this.tasksInProgressCount = tasksInProgressCount;
    }
}
