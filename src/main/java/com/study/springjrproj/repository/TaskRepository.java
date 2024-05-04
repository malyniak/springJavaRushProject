package com.study.springjrproj.repository;

import com.study.springjrproj.domain.Task;
import com.study.springjrproj.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Page<TaskDto> findAllBy(Pageable pageable);
}
