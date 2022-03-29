package com.bookstoreapi.bookstoreapi.client;

import org.springframework.stereotype.Component;

@Component
public class ClientFieldsFormatter {


    public void fieldsFormatter(Client client){
        nameFormatter(client);
        genderFormatter(client);
        telephoneFormatter(client);
    }

    private void nameFormatter(Client client){
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
        }
    }

    private void genderFormatter(Client client){
        String gender = client.getGender().substring(0,1).toUpperCase()+client.getGender().substring(1);
        client.setGender(gender);
    }

    private void telephoneFormatter(Client client){
        String telephone = client.getTelephone();
        String formatedTelephone = "(" + telephone.substring(0, 2) + ") " + telephone.substring(2, 7) +
                "-" + telephone.substring(7);
        client.setTelephone(formatedTelephone);
    }
}
