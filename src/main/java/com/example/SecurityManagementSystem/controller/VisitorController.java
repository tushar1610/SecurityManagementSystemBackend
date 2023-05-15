package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.service.VisitorService;
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

    @GetMapping("/getAllVisitorsByName/{visitorName}")
    public List<Visitor> getAllVisitorsByName(@PathVariable String visitorName){
        return visitorService.getAllVisitorsByName(visitorName);
    }

    @GetMapping("/getAllVisitorsByAge/{age}")
    public List<Visitor> getAllVisitorsByAge(@PathVariable Integer age){
        return visitorService.getAllVisitorsByAge(age);
    }

    @GetMapping("/getAllVisitorsByGender/{gender}")
    public List<Visitor> getAllVisitorsByGender(@PathVariable String gender){
        return visitorService.getAllVisitorsByGender(gender);
    }

    @GetMapping("/getAllVisitorsByDate/{date}")
    public List<Visitor> getAllVisitorsByDate(@PathVariable Date date){
        return visitorService.getAllVisitorsByDate(date);
    }

    @PostMapping("/addVisitor")
    public Visitor addVisitor(@RequestBody Visitor visitor){
        Visitor visitor1 = visitorService.addVisitor(visitor);
        if (visitor1 == null) {
            //return VisitorNotCreatedException
        }
        return visitor1;
    }

    @PutMapping("/updateVisitorOutTime/{visitorName}/{flatNo}/{outTime}")
    public Visitor updateVisitorOutTime(@PathVariable String visitorName, @PathVariable String flatNo, @PathVariable String outTime){
        Visitor visitor = visitorService.updateVisitorOutTime(visitorName, flatNo, outTime);
        if (visitor == null) {
            //return VisitorNotAvailableException
        }
        return visitor;
    }

}
