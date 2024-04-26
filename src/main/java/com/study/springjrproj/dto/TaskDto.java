package com.study.springjrproj.dto;

import com.study.springjrproj.domain.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class TaskDto {
   private int id;
   private String description;
   private Status status;
}
