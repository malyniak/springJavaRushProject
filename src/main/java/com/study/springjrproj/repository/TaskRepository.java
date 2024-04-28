package com.study.springjrproj.repository;

import com.study.springjrproj.domain.Status;
import com.study.springjrproj.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {


    @Query(value = "update Task t set t.description = :description")
    Task updateByDescription(String description);

    @Query(value = "update Task t set t.status = :status")
    Task updateBy(Status status);

   @Query(value = "update Task t set t.description = 'dd' where t.id = :id")
   Task updateTaskById(Integer id);

}
