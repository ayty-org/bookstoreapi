package com.bookstoreapi.bookstoreapi.client;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {

    @Size(min = 3, max = 60, message = "name must be between 3 and 60 characters")
    @NotBlank(message = "name cannot be null or void")
    private String name;

    @Max(value = 120, message = "age cannot be higher than 120")
    @Min(value = 0, message = "age cannot be lower than 0")
    @NotNull(message = "age cannot be null")
    private Integer age;

    @Size(min = 11, max = 11, message = "telephone must be 11 digits")
    @NotBlank(message = "telephone cannot be null or void")
    private String telephone;

    @Email(message = "email invalid")
    @NotBlank(message = "email cannot be null or void")
    private String email;

    @Size(min = 4, max = 15, message = "gender must be between 4 and 15 characters")
    @NotBlank(message = "gender cannot be null or void")
    private String gender;


    public static ClientDTO from(Client client){
        return ClientDTO.builder()
                .name(client.getName())
                .age(client.getAge())
                .telephone(client.getTelephone())
                .email(client.getEmail())
                .gender(client.getGender())
                .build();
    }

    public static List<ClientDTO> fromAll (List<Client> clients) {
        return clients.stream()
                .map(ClientDTO::from)
                .collect(Collectors.toList());
    }

    public static Client from(ClientDTO clientDTO){
        return Client.builder()
                .name(clientDTO.getName())
                .age(clientDTO.getAge())
                .telephone(clientDTO.getTelephone())
                .email(clientDTO.getEmail())
                .gender(clientDTO.getGender())
                .build();
    }

}
