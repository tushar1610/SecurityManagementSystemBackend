package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.exception.VisitorNotCreatedException;
import com.example.SecurityManagementSystem.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/getAllVisitors")
    public List<Visitor> getAllVisitors() {
        return visitorService.getAllVisitors();
    }

    @GetMapping("/getAllVisitorsByAge/{age}")
    public List<Visitor> getAllVisitorsByAge(@PathVariable Integer age){
        return visitorService.getAllVisitorsByAge(age);
    }

    @GetMapping("/getAllVisitorsByGender/{gender}")
    public List<Visitor> getAllVisitorsByGender(@PathVariable String gender){
        return visitorService.getAllVisitorsByGender(gender);
    }

    @GetMapping("/getAllVisitorsByFlatNo/{flatNo}")
    public List<Visitor> getAllVisitorsByFlatNo(@PathVariable String flatNo){
        return visitorService.getAllVisitorsByFlatNo(flatNo);
    }

    @GetMapping("/getAllVisitorsByDate/{date}")
    public List<Visitor> getAllVisitorsByDate(@PathVariable Date date){
        return visitorService.getAllVisitorsByDate(date);
    }

    @PostMapping("/addVisitor/{guardName}")
    public Visitor addVisitor(@PathVariable String guardName, @RequestBody @Valid Visitor visitor) throws VisitorNotCreatedException {
        Visitor visitor1 = visitorService.addVisitor(visitor, guardName);
        if (visitor1 == null) {
            throw new VisitorNotCreatedException("Visitor cannot be created. Try again later.");
        }
        return visitor1;
    }

    @PutMapping("/updateVisitorOutTime")
    public Visitor updateVisitorOutTime(@RequestBody Visitor visitor) throws Exception {
        return visitorService.updateVisitorOutTime(visitor);

    }

    @PutMapping("/updateApprovalStatus/{visitorId}")
    public boolean updateVisitorApprovalStatus(@PathVariable Long visitorId, @RequestBody Visitor visitor){
        return visitorService.updateVisitorApprovalStatus(visitorId, visitor);
    }

}
