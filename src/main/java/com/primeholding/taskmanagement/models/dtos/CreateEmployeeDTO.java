package com.primeholding.taskmanagement.models.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CreateEmployeeDTO {

    private Long id;

    @NotBlank
    @Size(min = 4 , max = 30)
    private String fullName;

    @NotBlank
    @Email
    //@UniqueEmail TODO: annotation collides with edit functionality so it's temporary disabled
    private String email;

    @NotBlank
    @Size(min = 7, max = 15) //worldwide shortest (7digits) and longest (15digits) phoneNumbers
    private String phoneNumber;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotNull
    @Positive
    private BigDecimal monthlySalary;

    public CreateEmployeeDTO() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
