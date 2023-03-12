package com.primeholding.taskmanagement.web;

import com.primeholding.taskmanagement.models.dtos.*;
import com.primeholding.taskmanagement.services.EmployeeService;
import com.primeholding.taskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final EmployeeService employeeService;

    private final TaskService taskService;

    @Autowired
    public StatisticsController(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    @GetMapping
    public String displayStatistics(Model model) {

        List<DisplayTaskForFinishedPastMonthStatistics> tasksFinishedPastMonth = this.taskService.displayTasksFinishedPastMonth();
        model.addAttribute("tasksFinishedPastMonth", tasksFinishedPastMonth);

        return "statistics";
    }

    @GetMapping("productiveEmployees")
    public String displayProductiveEmployees(Model model) {
        List<DisplayProductiveEmployee> productiveEmployees = this.employeeService.getTop5Employees();
        model.addAttribute("productiveEmployees", productiveEmployees);
        return "productiveEmployees";
    }

    @GetMapping("/tasksInProgress")
    public String displayTasksInProgress(Model model) {

        List<DisplayTaskInProgress> tasksInProgress = this.taskService.getTasksInProgress();
        model.addAttribute("tasksInProgress", tasksInProgress);
        return "tasksInProgress";
    }

    @GetMapping("/tasksDelayed")
    public String displayDelayedTasks(Model model) {

        List<DisplayDelayedTask> tasksDelayed = this.taskService.getDelayedTasks();
        model.addAttribute("tasksDelayed", tasksDelayed);
        return "delayedTasks";
    }

    @GetMapping("/tasksPerClient")
    public String displayTasksPerClient(Model model) {

        List<TotalTaskPerClient> tasksPerClient = this.taskService.getTasksPerClient();
        model.addAttribute("tasksPerClient", tasksPerClient);
        return "tasksPerClient";
    }

}
