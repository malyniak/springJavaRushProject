package com.study.springjrproj.service;

import com.study.springjrproj.domain.Task;
import com.study.springjrproj.dto.CreateTaskDto;
import com.study.springjrproj.dto.TaskDto;
import com.study.springjrproj.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    public List<TaskDto> findAll() {
        var tasks = taskRepository.findAll();
        var taskDtoStream = tasks.stream().map(task -> mapper.map(task, TaskDto.class));
        return taskDtoStream
                .collect(Collectors.toList());
    }
    public List<TaskDto> findAll(Pageable pageable) {
    return taskRepository.findAll(pageable)
            .stream().map(task -> mapper.map(task, TaskDto.class))
            .collect(Collectors.toList());
    }
    public Optional<TaskDto> getById(Integer id) {
        return Optional.ofNullable(taskRepository.findById(id)
                .map(task -> mapper.map(task, TaskDto.class))
                .orElseThrow(() -> new RuntimeException())); //todo custom exception 29.04
    }

    public void addTask(CreateTaskDto task) {
      taskRepository.save(mapper.map(task, Task.class));
    }
    public void update(TaskDto taskDto) {
        var task = mapper.map(taskDto, Task.class);
        taskRepository.save(task);
    }
    public void deleteById(Integer id) {
        var maybeTask = taskRepository.findById(id);
        maybeTask.ifPresent(taskRepository::delete);
    }
}
