package com.plannerapp.controller;

import com.plannerapp.model.dto.task.TaskHomeViewModel;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.impl.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final TaskService taskService;
    private final LoggedUser loggedUser;

    public HomeController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public ModelAndView index() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        TaskHomeViewModel viewModel = taskService.getHomeViewData(loggedUser.getUsername());

        return new ModelAndView("home", "tasks", viewModel);
    }
}
