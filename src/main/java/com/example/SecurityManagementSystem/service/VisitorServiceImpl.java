package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService{

    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public Visitor updateVisitorOutTime(String visitorName, String flatNo, String outTime) {
        if(!visitorRepository.findByVisitorNameAndSocietyUsersFlatNo(visitorName, flatNo).isPresent()){
            return null;
        }
        Visitor visitor = visitorRepository.findByVisitorNameAndSocietyUsersFlatNo(visitorName, flatNo).get();
        visitor.setOutTime(outTime);
        return visitorRepository.save(visitor);
    }

    @Override
    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public List<Visitor> getAllVisitorsByDate(Date date) {
        return visitorRepository.findAllByDate(date);
    }

    @Override
    public List<Visitor> getAllVisitorsByGender(String gender) {
        return visitorRepository.findAllByGender(gender);
    }

    @Override
    public List<Visitor> getAllVisitorsByAge(Integer age) {
        return visitorRepository.findAllByAge(age);
    }

    @Override
    public List<Visitor> getAllVisitorsByName(String visitorName) {
        return visitorRepository.findAllByVisitorName(visitorName);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }
}
