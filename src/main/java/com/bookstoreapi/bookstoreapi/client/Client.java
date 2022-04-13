package com.bookstoreapi.bookstoreapi.client;


import lombok.*;

import javax.persistence.*;

@Builder
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

}
