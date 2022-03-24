package com.bookstoreapi.bookstoreapi.user;

import com.bookstoreapi.bookstoreapi.exceptions.IllegalArgumentException;
import org.springframework.stereotype.Component;

@Component
public class UserFieldsVerification {

    String[] validGenders = {"Male", "Female", "Transgender woman", "Transgender man", "Other"};


    public void userFieldsAreValid(User user){
         emailIsValid(user.getEmail());
         genderIsValid(user.getGender());
         telephoneIsValid(user.getTelephone());
         ageIsValid(user.getAge());
    }

    private void emailIsValid(String email){
        if(!email.contains("@")){
            throw new IllegalArgumentException("email is invalid");
        }
    }

    private void genderIsValid(String gender){
        for(String validGender: validGenders){
            if(gender.equals(validGender)){
                return;
            }
        }
        throw new IllegalArgumentException("gender is invalid");

    }

    private void telephoneIsValid(String telephone){
        if(telephone.length() != 10){
            throw new IllegalArgumentException("telephone is invalid");
        }
    }

    private void ageIsValid(int age){
        if(age > 120){
            throw new IllegalArgumentException("telephone is invalid");
        }
    }
}
