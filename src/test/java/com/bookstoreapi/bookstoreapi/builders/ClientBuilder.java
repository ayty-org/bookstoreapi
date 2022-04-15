package com.bookstoreapi.bookstoreapi.builders;

import com.bookstoreapi.bookstoreapi.client.Client;

import java.util.LinkedList;
import java.util.List;

public class ClientBuilder {

    public static Client clientJenipapo1(){
        return Client.builder()
                .id(1L)
                .name("Jenipapo")
                .age(19)
                .email("jenipapo@coldmail.com")
                .telephone("83996438691")
                .gender("Male")
                .build();
    }

    public static Client clientAna2(){
        return Client.builder()
                .id(2L)
                .name("Ana")
                .age(46)
                .email("ana@coldmail.com")
                .telephone("83996438691")
                .gender("Female")
                .build();
    }

    public static Client clientPatricia3(){
        return Client.builder()
                .id(3L)
                .name("Patricia")
                .age(25)
                .email("patricia@coldmail.com")
                .telephone("83996438691")
                .gender("Trans")
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
        list.add(clientJenipapo1());
        list.add(clientAna2());
        list.add(clientPatricia3());
        return list;
    }
}
