package com.plannerapp.controller;

import com.plannerapp.model.dto.task.TasksAddBindingModel;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.impl.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TaskController {

    private final TaskService taskService;

    private final LoggedUser loggedUser;

    public TaskController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/tasks/add")
    public ModelAndView add(@ModelAttribute("tasksAddBindingModel") TasksAddBindingModel tasksAddBindingModel) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("task-add");
    }

    @PostMapping("/tasks/add")
    public ModelAndView add(
            @ModelAttribute("tasksAddBindingModel") @Valid TasksAddBindingModel tasksAddBindingModel,
            BindingResult bindingResult) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("task-add");
        }

        taskService.add(tasksAddBindingModel);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/tasks/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        taskService.remove(id);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/tasks/return/{id}")
    public ModelAndView returnTask(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        taskService.assign(id, null);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/tasks/assign/{id}")
    public ModelAndView assign(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        taskService.assign(id, loggedUser.getUsername());

        return new ModelAndView("redirect:/home");
    }
}
