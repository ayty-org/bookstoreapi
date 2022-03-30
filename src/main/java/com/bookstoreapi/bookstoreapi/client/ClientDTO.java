package com.bookstoreapi.bookstoreapi.client;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {

    private String name;
    private Integer age;
    private String telephone;
    private String email;
    private String gender;

    public ClientDTO(Client client){
        this.name = client.getName();
        this.age = client.getAge();
        this.telephone = client.getTelephone();
        this.email = client.getEmail();
        this.gender = client.getGender();
    }
}
