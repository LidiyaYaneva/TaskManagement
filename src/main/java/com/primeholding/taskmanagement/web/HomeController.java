package com.primeholding.taskmanagement.web;

import com.primeholding.taskmanagement.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final SeedService seedService;

    @Autowired
    public HomeController(SeedService seedService) {
        this.seedService = seedService;
    }


    @GetMapping
    public String displayHome(Model model) {

        boolean areEmployeesSeeded = this.seedService.areEmployeesSeeded();
        boolean areTasksSeeded = this.seedService.areTasksSeeded();
        model.addAttribute("areEmployeesSeeded", areEmployeesSeeded);
        model.addAttribute("areTasksSeeded", areTasksSeeded);

        return "home";
    }
}
