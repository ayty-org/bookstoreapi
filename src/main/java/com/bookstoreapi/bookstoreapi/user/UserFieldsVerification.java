package com.bookstoreapi.bookstoreapi.user;

import org.springframework.stereotype.Component;

@Component
public class UserFieldsVerification {

    String[] validGenders = {"Male", "Female","Trans Male", "Trans Female", "Non-binary"};


    public void userFieldsAreValid(User user){
        nameIsValid(user);
        emailIsValid(user);
        genderIsValid(user);
        telephoneIsValid(user);
        ageIsValid(user);
    }

    private void nameIsValid(User user) {
        if(user.getName() == null){
            throw new IllegalArgumentException("name invalid (cannot be null");
        }
        String[] names = user.getName().split(" ");
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
            user.setName(formatedName.substring(0,formatedName.length()-1));
            return;
        }
        throw new IllegalArgumentException("name invalid (must contain most 1 character)");
    }


    private void emailIsValid(User user){
        if(!user.getEmail().contains("@")){
            throw new IllegalArgumentException("email invalid");
        }
    }

    private void genderIsValid(User user){
        for(String validGender: validGenders){
            if(user.getGender().equalsIgnoreCase(validGender)){
                user.setGender(validGender);
                return;
            }
        }
        throw new IllegalArgumentException("gender invalid");

    }

    private void telephoneIsValid(User user){
        String telephone = user.getTelephone();
        try {
            Double.parseDouble(telephone);
            if(telephone.length() == 11){
                String formatedTelephone = "("+telephone.substring(0,2)+") "+telephone.substring(2,7)+"-"
                        +telephone.substring(7);
                user.setTelephone(formatedTelephone);
                return;
            }
            throw new IllegalArgumentException("telephone invalid (must contain 11 numbers)");
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("telephone invalid (only numbers are accepted)");
        }
    }

    private void ageIsValid(User user){
        if(user.getAge() < 5 || user.getAge() > 120){
            throw new IllegalArgumentException("age invalid (only integer numbers between 5 and 120)");
        }
    }
}
