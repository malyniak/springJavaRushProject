package com.study.springjrproj.service;

import com.study.springjrproj.dto.UserDto;
import com.study.springjrproj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
    //todo 5.05
}
