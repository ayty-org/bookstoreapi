package com.bookstoreapi.bookstoreapi.user;


import lombok.Data;

import javax.persistence.*;


@Table(name = "users")
@Data
@Entity
public class User {

    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private int age;
    private String telephone;
    private String email;
    private String gender;
}
