package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.Notification;
import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.exception.VisitorAlreadyExitException;
import com.example.SecurityManagementSystem.exception.VisitorNotFoundException;
import com.example.SecurityManagementSystem.repository.NotificationRepository;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VisitorServiceImpl implements VisitorService{

    public static final Logger logger = LoggerFactory.getLogger(VisitorServiceImpl.class);

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private SocietyUserRepository societyUserRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public Visitor updateVisitorOutTime(Visitor visitor) throws Exception {
        Optional<Visitor> visitor1 = visitorRepository.findByVisitorName(visitor.getVisitorName());
        if (!visitor1.isPresent()) {
            logger.error("Visitor data not available.");
            throw new VisitorNotFoundException("No such visitor entered.");
        }
        if (Objects.equals(visitor1.get().getOutTime(), "")){
            visitor1.get().setOutTime(visitor.getOutTime());
        } else {
            logger.error("OutTime is not null.");
            throw new VisitorAlreadyExitException("This visitor has already exited.");
        }
        return visitorRepository.save(visitor1.get());
    }

    @Override
    public Visitor addVisitor(Visitor visitor, String guardName) {
        SocietyUser societyUser = societyUserRepository.findByFlatNo(visitor.getSocietyUser().getFlatNo());
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
        Visitor savedVisitor = visitorRepository.save(visitor1);

        String message = "New visitor: " + savedVisitor.getVisitorName() + ". Sent by: " + savedVisitor.getGuardName();

        Notification notification = Notification.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);

        return savedVisitor;
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
