package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();
    Page<UserDto> getUsersWithPagination(Pageable pageable);


    Optional<UserDto> getUserById(Long id);

    UserDto createUser(UserDto userDTO);

    UserDto updateUser(Long id, UserDto userDTO);

    void deleteUser(Long id);

    void softDeleteUser(Long id);

}

