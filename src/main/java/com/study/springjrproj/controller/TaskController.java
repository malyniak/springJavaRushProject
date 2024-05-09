package com.study.springjrproj.controller;


import com.study.springjrproj.dto.CreateTaskDto;
import com.study.springjrproj.dto.TaskDto;
import com.study.springjrproj.exception.TaskNotFoundException;
import com.study.springjrproj.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Slf4j
public class TaskController {
    private final TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllTasks(Model model,
                              @RequestParam(defaultValue = "0", required = false) int page,
                              @RequestParam(defaultValue = "5", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        var tasksPerPage = taskService.findAllBy(pageable);
        model.addAttribute("tasksPerPage", tasksPerPage);
        log.trace("Added attribute - tasksPerPage");
        return "tasks";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute TaskDto taskToUpdate) {
        taskService.update(taskToUpdate);
        log.info("Updated task");
        return "redirect:/tasks";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        try {
            var taskDto = taskService.findById(id);
            model.addAttribute("task", taskDto.get());
            log.trace("Added attribute - task");
            return "edit";
        } catch (TaskNotFoundException e) {
            log.warn("Task not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            taskService.deleteById(id);
            log.info("Task was deleted");
            return "redirect:/tasks";
        } catch (TaskNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("task", new TaskDto());
        log.trace("Added attribute - task");
        return "create";
    }
    @PostMapping("/create")
    public String createForm(@ModelAttribute("task") CreateTaskDto taskDto) {
        taskService.save(taskDto);
        return "redirect:/tasks";
    }
}
