package com.example.journalApp.Service;

import com.example.journalApp.Entity.User;
import com.example.journalApp.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> userFindById(ObjectId id){
        return userRepo.findById(id);
    }

    public void userDeleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public User findUserByName(String username){
        return userRepo.findUserByUsername(username);
    }
}
