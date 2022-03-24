package com.bookstoreapi.bookstoreapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> list(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> find(@PathVariable Long userId){
        try{
            return  ResponseEntity.ok(userService.findById(userId));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id "+userId+" not found");
        }
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId, @RequestBody User user){
        try{
            return ResponseEntity.ok(userService.update(userId,user));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id "+userId+" not found");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable Long userId){
        try{
            userService.delete(userId);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id "+userId+" not found");
        }
    }

}
