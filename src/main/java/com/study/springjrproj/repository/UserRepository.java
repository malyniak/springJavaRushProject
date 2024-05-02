package com.study.springjrproj.repository;

import com.study.springjrproj.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
