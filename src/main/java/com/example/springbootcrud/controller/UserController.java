package com.example.springbootcrud.controller;

import com.example.springbootcrud.response.ApiResponse;
import com.example.springbootcrud.dto.UserDto;
import com.example.springbootcrud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        ApiResponse<List<UserDto>> response = new ApiResponse<>(true, "Success", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(userDto -> ResponseEntity.ok(new ApiResponse<>(true, "Success", userDto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Validated @RequestBody UserDto userDto) {
        UserDto newUserDto = userService.createUser(userDto);
        ApiResponse<UserDto> response = new ApiResponse<>(true, "User created successfully", newUserDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id, @Validated @RequestBody UserDto userDTO) {
        UserDto updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(new ApiResponse<>(true, "User updated successfully", updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse<>(true, "User deleted successfully", null), HttpStatus.NO_CONTENT);

    }

    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<Page<UserDto>>> getAllUsersWithPagination(Pageable pageable) {
        Page<UserDto> users = userService.getUsersWithPagination(pageable);
        return new ResponseEntity<>(new ApiResponse<>(true, "Users retrieved successfully", users), HttpStatus.OK);
    }
}
