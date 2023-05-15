package com.example.SecurityManagementSystem.repository;

import com.example.SecurityManagementSystem.entity.GuardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardUserRepository extends JpaRepository<GuardUser, Long> {

    GuardUser findByUserUserId(Long userId);

}
