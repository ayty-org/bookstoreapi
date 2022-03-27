package com.bookstoreapi.bookstoreapi.user;

import io.swagger.annotations.ApiOperation;
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


    @ApiOperation(value = "Return a list of all saved users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> list(){
        return userService.findAll();
    }

    @ApiOperation(value = "Returns a unique user found by id")
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User find(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @ApiOperation(value = "Save a new user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @ApiOperation(value = "Updates all fields of a saved user by the user passed as a parameter")
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable Long userId, @RequestBody User user){
        return userService.update(userId,user);
    }

    @ApiOperation(value = "Deletes a user by id")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId){
        userService.delete(userId);
    }
}
