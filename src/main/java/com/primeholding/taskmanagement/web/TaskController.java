package com.primeholding.taskmanagement.web;


import com.primeholding.taskmanagement.models.dtos.*;
import com.primeholding.taskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public String displayTasks(Model model) {

        List<DisplayTaskDTO> displayTaskDTOS = this.taskService.getTasksToDisplay();
        model.addAttribute("displayTaskDTOS", displayTaskDTOS);

        return "tasks";
    }

    @GetMapping("/find")
    public String displayFindTask(Model model, String inputTitle) {

        List<DisplayTaskDTO> displayTaskDTOS = this.taskService.searchTasksByTitle(inputTitle);
        model.addAttribute("displayTaskDTOS", displayTaskDTOS);
        model.addAttribute("inputTitle", inputTitle);

        return "searchTask";

    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {

        this.taskService.deleteTaskById(id);

        return "redirect:/tasks/find";

    }

    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable Long id) {

        this.taskService.completeTaskById(id);

        return "redirect:/tasks/find";

    }
}
