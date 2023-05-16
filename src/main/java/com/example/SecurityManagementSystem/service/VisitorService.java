package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.exception.VisitorAlreadyExitException;
import com.example.SecurityManagementSystem.exception.VisitorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface VisitorService {
    Visitor updateVisitorOutTime(Visitor visitor) throws Exception;

    Visitor addVisitor(Visitor visitor, String guardName);

    List<Visitor> getAllVisitorsByDate(Date date);

    List<Visitor> getAllVisitorsByGender(String gender);

    List<Visitor> getAllVisitorsByAge(Integer age);

    List<Visitor> getAllVisitors();

    List<Visitor> getAllVisitorsByFlatNo(String flatNo);
}
