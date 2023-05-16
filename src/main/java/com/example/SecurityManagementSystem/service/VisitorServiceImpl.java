package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.exception.VisitorAlreadyExitException;
import com.example.SecurityManagementSystem.exception.VisitorNotFoundException;
import com.example.SecurityManagementSystem.repository.GuardUserRepository;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VisitorServiceImpl implements VisitorService{

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private SocietyUserRepository societyUserRepository;

    @Autowired
    private GuardUserRepository guardUserRepository;

    @Override
    public Visitor updateVisitorOutTime(Visitor visitor) throws Exception {
        Optional<Visitor> visitor1 = visitorRepository.findByVisitorName(visitor.getVisitorName());
        if (!visitor1.isPresent()) {
            throw new VisitorNotFoundException("No such visitor entered.");
        }
        if (Objects.equals(visitor1.get().getOutTime(), "")){
            visitor1.get().setOutTime(visitor.getOutTime());
        } else {
            throw new VisitorAlreadyExitException("This visitor has already exited.");
        }
        return visitorRepository.save(visitor1.get());
    }

    @Override
    public Visitor addVisitor(Visitor visitor, String guardName) {
        SocietyUser societyUser = societyUserRepository.findByFlatNo(visitor.getSocietyUser().getFlatNo());
        System.out.println(societyUser.getOwnerName());
        Visitor visitor1 = Visitor.builder()
                .age(visitor.getAge())
                .contactNo(visitor.getContactNo())
                .gender(visitor.getGender())
                .date(visitor.getDate())
                .inTime(visitor.getInTime())
                .outTime("")
                .purpose(visitor.getPurpose())
                .visitorName(visitor.getVisitorName())
                .societyUser(societyUser)
                .guardName(guardName)
                .build();
        return visitorRepository.save(visitor1);
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
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public List<Visitor> getAllVisitorsByFlatNo(String flatNo) {
        return visitorRepository.findAllBySocietyUserFlatNo(flatNo);
    }
}
