package com.bookstoreapi.bookstoreapi.client;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "clients")
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
    private Integer age;
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
