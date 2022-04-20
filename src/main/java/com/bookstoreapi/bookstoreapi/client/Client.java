package com.bookstoreapi.bookstoreapi.client;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

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
    private UUID uuid;
    private String name;
    private Integer age;
    private String telephone;
    private String email;
    private String gender;

}
