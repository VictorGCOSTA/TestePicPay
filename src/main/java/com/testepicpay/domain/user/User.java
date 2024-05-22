package com.testepicpay.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType type;

    public User(UserDTO user) {
        this.firstName = user.firstname();
        this.lastName = user.lastname();
        this.document = user.document();
        this.email = user.email();
        this.balance = user.balance();
        this.type = user.type();
    }
}
