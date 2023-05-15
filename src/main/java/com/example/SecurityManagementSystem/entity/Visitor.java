package com.example.SecurityManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {

    @Id
    @SequenceGenerator(
            name = "visitor_id_sequence",
            sequenceName = "visitor_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "visitor_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long visitorId;
    @NotNull
    @Size(max = 100, min = 3, message = "Length must be greater than 3 and less than 100")
    private String visitorName;
    private Integer age;
    private String contactNo;
    private String gender;
    @NotNull
    private String purpose;
    @NotNull
    private String date;
    @NotNull
    private String inTime;
    @NotNull
    private String outTime;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = SocietyUser.class)
    @JoinColumn(
            name = "SocietyUserFlatNo",
            referencedColumnName = "flatNo"
    )
    private List<SocietyUser> societyUsers;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "GuardUserId",
            referencedColumnName = "gUserId"
    )
    private GuardUser guard;

}
