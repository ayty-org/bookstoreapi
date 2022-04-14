package com.bookstoreapi.bookstoreapi.builders;

import com.bookstoreapi.bookstoreapi.client.Client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClientBuilder {

    public static Client clientValid(){
        return Client.builder()
                .id(1L)
                .name("Jenipapo")
                .age(19)
                .email("jenipapo@coldmail.com")
                .telephone("83996438691")
                .gender("Male")
                .build();
    }

    public static Client clientInvalid(){
        return Client.builder()
                .id(1L)
                .name(null)
                .age(19)
                .email("jenipapo@coldmail.com")
                .telephone("83996438691")
                .gender("Male")
                .build();
    }


    public static List<Client> clientList(){
        List<Client> list = new LinkedList<>();
        for(Integer k=1; k<4; k++){
            Client client = ClientBuilder.clientValid();
            client.setId(Long.parseLong(k.toString()));
            list.add(client);
        }
        return list;
    }
}
