package com.bookstoreapi.bookstoreapi.user;

import org.springframework.stereotype.Component;

@Component
public class UserFieldsVerification {

    String[] validGenders = {"Male", "Female", "Other"};


    public void userFieldsAreValid(User user){
         emailIsValid(user.getEmail());
         genderIsValid(user.getGender());
         telephoneIsValid(user.getTelephone());
         ageIsValid(user.getAge());
    }

    private void emailIsValid(String email){
        if(!email.contains("@")){
            throw new IllegalArgumentException("email invalid");
        }
    }

    private void genderIsValid(String gender){
        for(String validGender: validGenders){
            if(gender.equalsIgnoreCase(validGender)){
                return;
            }
        }
        throw new IllegalArgumentException("gender invalid");

    }

    private void telephoneIsValid(String telephone){
        if(telephone.length() != 11){
            throw new IllegalArgumentException("telephone invalid");
        }
    }

    private void ageIsValid(int age){
        if(age > 120){
            throw new IllegalArgumentException("age invalid");
        }
    }
}
