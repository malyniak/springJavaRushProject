package com.study.springjrproj.controller;


import com.study.springjrproj.repository.TaskRepository;
import com.study.springjrproj.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor

public class TaskController {
    private final TaskService taskService;


    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public String getAllTasks(Model model) {

        var tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
}
