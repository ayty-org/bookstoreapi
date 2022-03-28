package com.bookstoreapi.bookstoreapi.client;

import org.springframework.stereotype.Component;

@Component
public class ClientFieldsVerification {

    String[] validGenders = {"Male", "Female","Trans Male", "Trans Female", "Non-binary"};


    public void clientFieldsVerification(Client client){
        nameIsValid(client);
        emailIsValid(client);
        genderIsValid(client);
        telephoneIsValid(client);
        ageIsValid(client);
    }

    private void nameIsValid(Client client) {
        if(client.getName() == null){
            throw new IllegalArgumentException("name invalid (cannot be null");
        }
        String[] names = client.getName().split(" ");
        StringBuilder formatedName = new StringBuilder();
        for (String name : names) {
            if(name.length()>0) {
                if (!name.equals("dos") && !name.equals("das")
                        && !name.equals("do") && !name.equals("da") && !name.equals("de")) {
                    formatedName.append(name.substring(0, 1).toUpperCase())
                            .append(name.substring(1)).append(" ");
                } else {
                    formatedName.append(name).append(" ");
                }
            }
        }
        if(formatedName.length() > 2){
            client.setName(formatedName.substring(0,formatedName.length()-1));
            return;
        }
        throw new IllegalArgumentException("name invalid (must contain at least more than 2)");
    }


    private void emailIsValid(Client client){
        if(!client.getEmail().contains("@")){
            throw new IllegalArgumentException("email invalid");
        }
    }

    private void genderIsValid(Client client){
        for(String validGender: validGenders){
            if(client.getGender().equalsIgnoreCase(validGender)){
                client.setGender(validGender);
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
                String formatedTelephone = "(" + telephone.substring(0, 2) + ") " + telephone.substring(2, 7) +
                        "-" + telephone.substring(7);
                client.setTelephone(formatedTelephone);
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
