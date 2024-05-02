package com.study.springjrproj.controller;


import com.study.springjrproj.dto.CreateTaskDto;
import com.study.springjrproj.dto.TaskDto;
import com.study.springjrproj.exception.TaskNotFoundException;
import com.study.springjrproj.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return "redirect:/tasks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        try {
            var taskDto = taskService.getById(id);
            model.addAttribute("task", taskDto.get());
            return "edit";
        } catch (TaskNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            taskService.deleteById(id);
            return "redirect:/tasks";
        } catch (TaskNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("task", new TaskDto());
        return "create";
    }

    @PostMapping("/create")
    public String createForm(@ModelAttribute("task") CreateTaskDto taskDto) {
        taskService.save(taskDto);
        return "redirect:/tasks";
    }
}
