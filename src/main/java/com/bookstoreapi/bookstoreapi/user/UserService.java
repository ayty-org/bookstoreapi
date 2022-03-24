package com.bookstoreapi.bookstoreapi.user;

import com.bookstoreapi.bookstoreapi.exceptions.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFieldsVerification userFieldsVerification;


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("User with id "+id+" not found");
        });
    }

    public User save(User user){
        userFieldsVerification.userFieldsAreValid(user);
        return userRepository.save(user);
    }

    public void delete(Long id){
        User userSaved = this.findById(id);
        userRepository.delete(userSaved);
    }

    public User update(Long id, User user){
        User userSaved = this.findById(id);
        userFieldsVerification.userFieldsAreValid(user);
        BeanUtils.copyProperties(user, userSaved, "id");
        return this.save(userSaved);
    }
}
