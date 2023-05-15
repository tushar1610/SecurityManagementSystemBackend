package com.example.SecurityManagementSystem.repository;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyUserRepository extends JpaRepository<SocietyUser, Long> {
    SocietyUser findByUserUserId(Long userId);
}
