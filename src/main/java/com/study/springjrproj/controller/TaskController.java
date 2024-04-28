package com.study.springjrproj.controller;


import com.study.springjrproj.domain.Task;
import com.study.springjrproj.dto.TaskDto;
import com.study.springjrproj.repository.TaskRepository;
import com.study.springjrproj.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;


    @RequestMapping(method = RequestMethod.GET)
    public String getAllTasks(Model model) {

        var tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute TaskDto taskToUpdate) {
        taskService.update(taskToUpdate);
        var all = taskService.findAll();
        return "redirect:/tasks";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        var taskDto = taskService.getById(id);
        model.addAttribute("task", taskDto.get());
        return "edit";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
