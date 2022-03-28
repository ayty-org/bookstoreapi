package com.bookstoreapi.bookstoreapi.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        userFieldsVerification.userFieldsVerification(user);
        return userRepository.save(user);
    }

    public void delete(Long id){
        User userSaved = this.findById(id);
        userRepository.delete(userSaved);
    }

    public User update(Long id, User user){
        User userSaved = this.findById(id);
        BeanUtils.copyProperties(user, userSaved, "id");
        return this.save(userSaved);
    }
}
