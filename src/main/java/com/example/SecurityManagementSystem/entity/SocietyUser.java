package com.example.SecurityManagementSystem.entity;

import jakarta.persistence.*;
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
public class SocietyUser {

    @Id
//    @SequenceGenerator(
//            name = "society_user_id_sequence",
//            sequenceName = "society_user_id_sequence",
//            allocationSize = 1
//    )
    @GeneratedValue(
//            generator = "society_user_id_sequence",
            strategy = GenerationType.AUTO
    )
    private Long sUserId;
    @NotNull
    @Size(min = 4, max = 4)
    @Column(unique = true)
    private String flatNo;
    private Boolean isAdmin;
    @NotNull
    @Size(min = 3)
    private String ownerName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "UserId",
            referencedColumnName = "userId"
    )
    private User user;

}
