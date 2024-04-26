package com.study.springjrproj.dto;

import com.study.springjrproj.domain.Status;
import lombok.Value;

@Value
public class CreateTaskDto {
    String description;
    Status status;

}
