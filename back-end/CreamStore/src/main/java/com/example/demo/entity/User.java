package com.example.demo.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name="email", unique = true)
    private String email;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="role", unique = true)
    private int role;

    @NotNull
    @Column(name="full_name")
    private String full_name;

    @NotNull
    @Column(name="numberphone")
    private String numberphone;

    @NotNull
    @Column(name="cash")
    private Float cash;

    @OneToMany(mappedBy = "user")
    private List<CartItem> cartItems;
//
//    @OneToMany(mappedBy = "user")
//    private List<Transaction> transactions;
}
