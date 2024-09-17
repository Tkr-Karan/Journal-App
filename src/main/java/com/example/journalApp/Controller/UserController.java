package com.example.journalApp.Controller;

import com.example.journalApp.Entity.User;
import com.example.journalApp.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User myUser = userService.findUserByName(userName);

        if(myUser != null){
            myUser.setUsername(user.getUsername());
            myUser.setPassword(user.getPassword());
            userService.saveUser((myUser));
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
