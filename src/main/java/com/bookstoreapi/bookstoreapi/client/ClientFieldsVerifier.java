package com.bookstoreapi.bookstoreapi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientFieldsVerifier {

    @Autowired
    private ClientFieldsFormatter clientFieldsFormatter;
    String[] validGenders = {"Male", "Female","Trans Male", "Trans Female", "Non-binary"};


    public void clientFieldsVerification(Client client){
        nameIsValid(client);
        emailIsValid(client);
        genderIsValid(client);
        telephoneIsValid(client);
        ageIsValid(client);

        clientFieldsFormatter.fieldsFormatter(client);
    }

    private void nameIsValid(Client client) {
        if(client.getName() == null){
            throw new IllegalArgumentException("name invalid (cannot be null");
        }
        int nameLenght = client.getName().length();
        if(nameLenght < 3 || nameLenght > 50){
            throw new IllegalArgumentException("name invalid (must contain between 3 and 50 characters)");
        }
    }


    private void emailIsValid(Client client){
        if(!client.getEmail().contains("@")){
            throw new IllegalArgumentException("email invalid");
        }
    }

    private void genderIsValid(Client client){
        for(String validGender: validGenders){
            if(client.getGender().equalsIgnoreCase(validGender)){
                return;
            }
        }
        throw new IllegalArgumentException("gender invalid");

    }

    private void telephoneIsValid(Client client){
        String telephone = client.getTelephone();
        if(client.getTelephone() != null && client.getTelephone().length()==11){
            try {
                Double.parseDouble(telephone);
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("telephone invalid (only numbers are accepted)");
            }
        }else{
            throw new IllegalArgumentException("telephone invalid (must contain 11 numbers)");
        }
    }

    private void ageIsValid(Client client){
        if(client.getAge() < 5 || client.getAge() > 120){
            throw new IllegalArgumentException("age invalid (only integer numbers between 5 and 120)");
        }
    }
}
