package com.example.SecurityManagementSystem.repository;

import com.example.SecurityManagementSystem.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    List<Visitor> findAllBySocietyUserFlatNo(String flatNo);

    Optional<Visitor> findByVisitorNameAndSocietyUserFlatNo(String visitorName, String flatNo);

    List<Visitor> findAllByDate(Date date);

    List<Visitor> findAllByAge(Integer age);
    List<Visitor> findAllByGender(String gender);

    Optional<Visitor> findByVisitorName(String visitorName);

}
