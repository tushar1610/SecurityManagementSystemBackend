package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.Visitor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface VisitorService {
    Visitor updateVisitorOutTime(String visitorName, String flatNo, String outTime);

    Visitor addVisitor(Visitor visitor);

    List<Visitor> getAllVisitorsByDate(Date date);

    List<Visitor> getAllVisitorsByGender(String gender);

    List<Visitor> getAllVisitorsByAge(Integer age);

    List<Visitor> getAllVisitorsByName(String visitorName);

    List<Visitor> getAllVisitors();
}
