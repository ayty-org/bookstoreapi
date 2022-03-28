package com.bookstoreapi.bookstoreapi.client;


import lombok.Data;

import javax.persistence.*;


@Table(name = "clients")
@Data
@Entity
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private int age;
    private String telephone;
    private String email;
    private String gender;
}
