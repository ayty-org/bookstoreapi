package com.bookstoreapi.bookstoreapi.client;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    public Client(ClientDTO clientDTO){
        this.name = clientDTO.getName();
        this.age = clientDTO.getAge();
        this.telephone = clientDTO.getTelephone();
        this.email = clientDTO.getEmail();
        this.gender = clientDTO.getGender();
    }
}
