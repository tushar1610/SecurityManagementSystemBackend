package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.Visitor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface VisitorService {
    Visitor updateVisitorOutTime(Visitor visitor) throws Exception;

    Visitor addVisitor(Visitor visitor);

    List<Visitor> getAllVisitorsByDate(LocalDate date);

    List<Visitor> getAllVisitorsByGender(String gender);

    List<Visitor> getAllVisitorsByAge(Integer age);

    List<Visitor> getAllVisitors();

    List<Visitor> getAllVisitorsByFlatNo(String flatNo);

    boolean updateVisitorApprovalStatus(Long visitorId, Visitor visitor);
}
