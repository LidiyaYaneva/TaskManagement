package com.primeholding.taskmanagement.web;


import com.primeholding.taskmanagement.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/seed")
public class SeedController {

    private final SeedService seedService;

    @Autowired
    public SeedController(SeedService seedService) {
        this.seedService = seedService;
    }

    @GetMapping("/employees")
    public String seedEmployees(Model model) {

        return "seedEmployees";

    }

    @PostMapping("/importEmployees")
    public String seedEmployeesPost(Model model) throws IOException {

        this.seedService.seedEmployees();
        return "redirect:/employees";

    }

    @GetMapping("/tasks")
    public String seedTasks(Model model) {

        return "seedTasks";

    }

    @PostMapping("/importTasks")
    public String seedTasksPost(Model model) throws IOException {

        this.seedService.seedTasks();
        return "redirect:/tasks";

    }
}
