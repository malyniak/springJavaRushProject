package com.study.springjrproj.service;

import com.study.springjrproj.domain.Task;
import com.study.springjrproj.dto.CreateTaskDto;
import com.study.springjrproj.dto.TaskDto;
import com.study.springjrproj.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
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

    public void addTask(CreateTaskDto task) {
      taskRepository.save(mapper.map(task, Task.class));
    }
    public void update(TaskDto taskDto) {
        //todo
    }
    public void deleteById(Integer id) {
        var maybeTask = taskRepository.findById(id);
        maybeTask.ifPresent(taskRepository::delete);
    }
}
