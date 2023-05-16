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
public class GuardUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long gUserId;
    @NotNull
    private String shiftTime;
    @NotNull
    @Size(min = 3, max = 500)
    private String address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "UserId",
            referencedColumnName = "userId"
    )
    private User user;
}
