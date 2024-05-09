package com.study.springjrproj.service;

import com.study.springjrproj.IntegrationTestBase;
import com.study.springjrproj.SpringJRProjApplication;
import com.study.springjrproj.domain.Status;
import com.study.springjrproj.domain.Task;
import com.study.springjrproj.dto.CreateTaskDto;
import com.study.springjrproj.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringJRProjApplication.class,
        initializers = ConfigDataApplicationContextInitializer.class)
class TaskServiceIT extends IntegrationTestBase {
    private static final int TASK_ID = 1;
    private static final int TASKS_SIZE = 15;
    private static final int pageNumber = 0;
    private static final int pageSize = 5;
    private static final String UPDATE_DESCRIPTION = "test";
    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    @Autowired
    public TaskServiceIT(TaskRepository taskRepository, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Test
    public void findById() {
        var task = taskRepository.findById(TASK_ID);
        assertTrue(task.isPresent());
        assertNotNull(task);
    }

    @Test
    public void findAll() {
        var tasks = taskRepository.findAll();
        assertEquals(TASKS_SIZE, tasks.size());
    }

    @Test
    public void findAllPageable() {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        var tasks = taskRepository.findAll(pageable);
        assertEquals(TASKS_SIZE, tasks.getTotalElements());
    }

    @Test
    @Rollback
    public void save() {
        var tasksBefore = taskRepository.findAll();
        var createTaskDto = new CreateTaskDto("test", Status.PAUSED);
        var task = mapper.map(createTaskDto, Task.class);
        taskRepository.saveAndFlush(task);
        var tasksAfter = taskRepository.findAll();
        assertNotEquals(tasksAfter, tasksBefore);
    }

    @Test
    @Rollback
    public void update() {
        var maybeTask = taskRepository.findById(TASK_ID);
        var task = maybeTask.get();
        assertNotNull(task);
        task.setDescription(UPDATE_DESCRIPTION);
        taskRepository.saveAndFlush(task);
        assertEquals(task.getDescription(), UPDATE_DESCRIPTION);
    }

    @Test
    @Rollback
    public void delete() {
        var tasksSize = taskRepository.findAll().size();
        var maybeTask = taskRepository.findById(TASK_ID);
        var task = maybeTask.get();
        taskRepository.delete(task);
        taskRepository.flush();
        Assertions.assertTrue(taskRepository.findById(TASK_ID).isEmpty());
        Assertions.assertNotEquals(taskRepository.findAll().size(), tasksSize);
    }
}
