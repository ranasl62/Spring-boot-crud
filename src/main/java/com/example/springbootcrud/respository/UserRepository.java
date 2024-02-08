package com.example.springbootcrud.respository;

import com.example.springbootcrud.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByIsDeletedFalse(); // Custom query for excluding soft-deleted records

    Page<UserEntity> findByIsDeletedFalse(Pageable pageable); // Support for pagination

    Optional<UserEntity> findByIdAndIsDeletedFalse(Long id); // Custom query for excluding soft-deleted records
}
