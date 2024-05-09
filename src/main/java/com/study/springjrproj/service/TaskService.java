package com.study.springjrproj.service;

import com.study.springjrproj.domain.Task;
import com.study.springjrproj.dto.CreateTaskDto;
import com.study.springjrproj.dto.TaskDto;
import com.study.springjrproj.exception.TaskNotFoundException;
import com.study.springjrproj.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    public List<TaskDto> findAll() {
        var tasks = taskRepository.findAll();
        log.info("Received {} tasks", tasks.size());
        var taskDtoStream = tasks.stream().map(task -> mapper.map(task, TaskDto.class));
        return taskDtoStream
                .collect(Collectors.toList());
    }

    public Page<TaskDto> findAllBy(Pageable pageable) {
        var tasks = taskRepository.findAll(pageable);
        log.info("Received {} tasks on the page", tasks.stream().count());
        return tasks.map(task -> mapper.map(task, TaskDto.class));
    }

    public Optional<TaskDto> findById(Integer id) {
        return Optional.ofNullable(taskRepository.findById(id)
                .map(task -> mapper.map(task, TaskDto.class))
                .orElseThrow(() -> new TaskNotFoundException("Task with" + id + " not found")));
    }

    public void update(TaskDto taskDto) {
        var task = mapper.map(taskDto, Task.class);
        log.info("Received task {}", task);
        taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        var maybeTask = taskRepository.findById(id);
        maybeTask.ifPresent(task -> {
            log.info("Received task {}", task);
            taskRepository.delete(task);
        });
        maybeTask.orElseThrow(() -> new TaskNotFoundException("Task with id {} not found"));
    }

    public void save(CreateTaskDto createTaskDto) {
        var task = mapper.map(createTaskDto, Task.class);
        taskRepository.saveAndFlush(task);
        if (taskRepository.findById(task.getId()).isPresent()) {
            log.info("Task was added successfully");
        } else {
            log.info("Task not found");
        }
    }
}
