package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.exception.VisitorNotCreatedException;
import com.example.SecurityManagementSystem.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @GetMapping("/getAllVisitors")
    public List<Visitor> getAllVisitors() {
        return visitorService.getAllVisitors();
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllVisitorsByAge/{age}")
    public List<Visitor> getAllVisitorsByAge(@PathVariable Integer age){
        return visitorService.getAllVisitorsByAge(age);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllVisitorsByGender/{gender}")
    public List<Visitor> getAllVisitorsByGender(@PathVariable String gender){
        return visitorService.getAllVisitorsByGender(gender);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @GetMapping("/getAllVisitorsByFlatNo/{flatNo}")
    public List<Visitor> getAllVisitorsByFlatNo(@PathVariable String flatNo){
        return visitorService.getAllVisitorsByFlatNo(flatNo);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllVisitorsByDate")
    public List<Visitor> getAllVisitorsByDate(@RequestParam("date") LocalDate date){
        return visitorService.getAllVisitorsByDate(date);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PostMapping("/addVisitor")
    public Visitor addVisitor(@RequestBody @Valid Visitor visitor) throws VisitorNotCreatedException {
        Visitor visitor1 = visitorService.addVisitor(visitor);
        if (visitor1 == null) {
            throw new VisitorNotCreatedException("Visitor cannot be created. Try again later.");
        }
        return visitor1;
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_GUARD_USER')")
    @PutMapping("/updateVisitorOutTime")
    public Visitor updateVisitorOutTime(@RequestBody Visitor visitor) throws Exception {
        return visitorService.updateVisitorOutTime(visitor);

    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @PutMapping("/updateApprovalStatus/{visitorId}")
    public boolean updateVisitorApprovalStatus(@PathVariable Long visitorId, @RequestBody Visitor visitor){
        return visitorService.updateVisitorApprovalStatus(visitorId, visitor);
    }

}
