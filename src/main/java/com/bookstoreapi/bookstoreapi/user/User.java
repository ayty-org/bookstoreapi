package com.bookstoreapi.bookstoreapi.user;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
