package com.example.springbootcrud.service.implementation;

import com.example.springbootcrud.dto.UserDto;
import com.example.springbootcrud.model.UserEntity;
import com.example.springbootcrud.respository.UserRepository;
import com.example.springbootcrud.service.ModelMapperService;
import com.example.springbootcrud.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;

    public UserServiceImpl(UserRepository userRepository, ModelMapperService modelMapperService) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
    }

    // Constructor

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findByIsDeletedFalse();
        return userEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findByIdAndIsDeletedFalse(id).map(this::convertToDto);
    }

    @Override
    public UserDto createUser(UserDto userDTO) {
        UserEntity userEntity = convertToEntity(userDTO);
        userEntity.setDeleted(false);
        UserEntity createdUserEntity = userRepository.save(userEntity);
        return convertToDto(createdUserEntity);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDTO) {
        if (userRepository.findByIdAndIsDeletedFalse(id).isPresent()) {
            userDTO.setId(id);
            UserEntity updatedUserEntity = userRepository.save(convertToEntity(userDTO));
            return convertToDto(updatedUserEntity);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void softDeleteUser(Long id) {
        userRepository.findById(id).ifPresent(userEntity -> {
            userEntity.setDeleted(true);
            userRepository.save(userEntity);
        });
    }

    @Override
    public Page<UserDto> getUsersWithPagination(Pageable pageable) {
        Page<UserEntity> userPage = userRepository.findByIsDeletedFalse(pageable);
        return userPage.map(this::convertToDto);
    }

    private UserDto convertToDto(UserEntity userEntity) {
        return this.modelMapperService.mapSourceToTarget(userEntity, UserDto.class);
    }

    private UserEntity convertToEntity(UserDto userDTO) {
        return this.modelMapperService.mapSourceToTarget(userDTO, UserEntity.class);
    }
}
