package com.primeholding.taskmanagement.web;

import com.primeholding.taskmanagement.models.dtos.ClientDTO;
import com.primeholding.taskmanagement.models.dtos.CreateTaskDTO;
import com.primeholding.taskmanagement.models.dtos.SelectEmployeeDTO;
import com.primeholding.taskmanagement.services.ClientService;
import com.primeholding.taskmanagement.services.EmployeeService;
import com.primeholding.taskmanagement.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class CreateTaskController {

    private final TaskService taskService;

    private final EmployeeService employeeService;

    private final ClientService clientService;

    @Autowired
    public CreateTaskController(TaskService taskService, EmployeeService employeeService, ClientService clientService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    @ModelAttribute("createTaskDTO")
    public CreateTaskDTO init(Model model) {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        model.addAttribute("createTaskDTO", createTaskDTO);
        return createTaskDTO;
    }

    @GetMapping("/create")
    public String displayCreateTask(Model model) {
        List<SelectEmployeeDTO> selectEmployeeDTOS = this.employeeService.getEmployeesNames();
        model.addAttribute("selectEmployeeDTOS", selectEmployeeDTOS);
        List<ClientDTO> clientDTOS = this.clientService.getClientsNames();
        model.addAttribute("clientDTOS", clientDTOS);
        return "createTask";
    }

    @PostMapping("/save")
    public String saveTask(@Valid CreateTaskDTO createTaskDTO, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createTaskDTO", createTaskDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createTaskDTO", bindingResult);
            return "redirect:/tasks/create";
        }
        this.taskService.saveTask(createTaskDTO);
        return "redirect:/tasks";

    }

    @GetMapping("/edit/{id}")
    public String displayUpdateTask(@PathVariable Long id, Model model) {

        CreateTaskDTO createTaskDTO = this.taskService.getById(id).orElseThrow();
        model.addAttribute("createTaskDTO", createTaskDTO);

        List<SelectEmployeeDTO> selectEmployeeDTOS = this.employeeService.getEmployeesNames();
        model.addAttribute("selectEmployeeDTOS", selectEmployeeDTOS);
        List<ClientDTO> clientDTOS = this.clientService.getClientsNames();
        model.addAttribute("clientDTOS", clientDTOS);

        return "createTask";

    }
}
