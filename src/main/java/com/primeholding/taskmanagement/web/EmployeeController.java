package com.primeholding.taskmanagement.web;


import com.primeholding.taskmanagement.models.dtos.CreateEmployeeDTO;
import com.primeholding.taskmanagement.models.dtos.DisplayEmployeeInfo;
import com.primeholding.taskmanagement.models.dtos.DisplayEmployeeWithStatsDTO;
import com.primeholding.taskmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public String displayEmployees(Model model) {

        List<DisplayEmployeeWithStatsDTO> displayEmployeeWithStatsDTOS = this.employeeService.getEmployeesToDisplay();
        model.addAttribute("displayEmployeeWithStatsDTOS", displayEmployeeWithStatsDTOS);

        return "employees";
    }

    @GetMapping("/find")
    public String displayFindEmployee(Model model, String inputName) {

        List<DisplayEmployeeInfo> displayEmployeeInfo = this.employeeService.searchEmployeesByName(inputName);
        model.addAttribute("displayEmployeeInfo", displayEmployeeInfo);
        model.addAttribute("inputName", inputName);

        return "searchEmployee";

    }

    @GetMapping("/edit/{id}")
    public String displayUpdateEmployee(@PathVariable Long id, Model model) {

        CreateEmployeeDTO createEmployeeDTO = this.employeeService.getById(id).orElseThrow();
        model.addAttribute("createEmployeeDTO", createEmployeeDTO);

        return "createEmployee";

    }

    @GetMapping("/delete/{id}")
    public String displayDeleteEmployee(@PathVariable Long id) {

        this.employeeService.deleteEmployee(id);

        return "redirect:/employees/find";

    }


}
