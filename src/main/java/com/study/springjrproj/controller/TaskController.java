package com.study.springjrproj.controller;


import com.study.springjrproj.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor

public class TaskController {
    private final TaskRepository taskRepository;

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public String hello() {
        var modelAndView = new ModelAndView();
        var tasks = taskRepository.findAll();
        modelAndView.addObject("tasks", tasks);
        modelAndView.setViewName("tasks");
        return "tasks";
    }
}
