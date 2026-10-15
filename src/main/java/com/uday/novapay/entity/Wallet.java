package com.uday.novapay.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Getter @Setter@NoArgsConstructor@AllArgsConstructor@Builder
public class Wallet {

    @Id@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name= "user_id")
    private User user;
}
