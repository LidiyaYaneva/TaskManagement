package com.primeholding.taskmanagement.services;


import com.primeholding.taskmanagement.models.dtos.*;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    void saveEmployee(CreateEmployeeDTO createEmployeeDTO);

    List<SelectEmployeeDTO> getEmployeesNames();

    List<DisplayEmployeeWithStatsDTO> getEmployeesToDisplay();

    List<DisplayEmployeeInfo> searchEmployeesByName(String inputName);

    void deleteEmployee(Long id);

    List<DisplayProductiveEmployee> getTop5Employees();

    Optional<CreateEmployeeDTO> getById(Long id);
}
