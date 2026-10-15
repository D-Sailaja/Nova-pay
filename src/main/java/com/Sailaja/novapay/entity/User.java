package com.Sailaja .novapay.entity;
import com.Sailaja .novapay.enums.UserRole;
import com.Sailaja .novapay.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 50)
    private String fullName;
    @Column(nullable=false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;
}
