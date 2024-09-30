package com.example.journalApp.Service;



import com.example.journalApp.Repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTest  {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserNameTest(){
        when( userRepo.findUserByUsername(ArgumentMatchers.anyString())).thenReturn((com.example.journalApp.Entity.User) User.builder().username("karan").password("sfwef").roles(String.valueOf(new ArrayList<>())).build());

        UserDetails user = userDetailsService.loadUserByUsername("karan");
        Assertions.assertNotNull(user);
    }
}
