package com.example.SecurityManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
//    @SequenceGenerator(
//            name = "user_id_sequence",
//            sequenceName = "user_id_sequence",
//            allocationSize = 1
//    )
    @GeneratedValue(
            //generator = "user_id_sequence",
            strategy = GenerationType.AUTO
    )
    private Long userId;
    @NotNull
    @Size(max = 100, min = 3, message = "Length must be greater than 3 and less than 100")
    private String userName;
    private Integer age;
    private String contactNo;
    @Email
    private String email;
    @Size(min = 3, max = 12)
    private String password;
    private String gender;

}
