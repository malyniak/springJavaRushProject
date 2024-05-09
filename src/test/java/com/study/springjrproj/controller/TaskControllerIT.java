package com.study.springjrproj.controller;

import com.study.springjrproj.IntegrationTestBase;
import com.study.springjrproj.SpringJRProjApplication;
import com.study.springjrproj.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringJRProjApplication.class,
        initializers = ConfigDataApplicationContextInitializer.class)
class TaskControllerIT extends IntegrationTestBase {

    MockMvc mockMvc;

    TaskService taskService;
    TaskController taskController;

    @Autowired
    public TaskControllerIT(TaskService taskService, TaskController taskController) {
        this.taskService = taskService;
        this.taskController = taskController;
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void getAllTasks() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("tasks"))
                    .andExpect(model().attributeExists("tasksPerPage"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
