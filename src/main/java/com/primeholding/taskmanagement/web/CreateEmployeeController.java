package com.primeholding.taskmanagement.web;

import com.primeholding.taskmanagement.models.dtos.CreateEmployeeDTO;
import com.primeholding.taskmanagement.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class CreateEmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public CreateEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ModelAttribute("createEmployeeDTO")
    public CreateEmployeeDTO init(Model model) {
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO();
        model.addAttribute("createEmployeeDTO", createEmployeeDTO);
        return createEmployeeDTO;
    }

    @GetMapping("/create")
    public String displayCreateEmployee() {
        return "createEmployee";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid CreateEmployeeDTO createEmployeeDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createEmployeeDTO", createEmployeeDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createEmployeeDTO", bindingResult);
            return "redirect:/employees/create";
        }
        this.employeeService.saveEmployee(createEmployeeDTO);
        return "redirect:/employees";

    }


}
