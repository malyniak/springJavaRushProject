package com.study.springjrproj.dto;

import com.study.springjrproj.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
   private int id;
   private String description;
   private Status status;
}
