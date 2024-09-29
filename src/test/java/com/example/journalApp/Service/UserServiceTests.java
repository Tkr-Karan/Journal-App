package com.example.journalApp.Service;

import com.example.journalApp.Repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepo userRepo;

    @Test
    public void testFindByUserName(){
        // assertEquals(4, 2+2);
        assertNotNull(userRepo.findUserByUsername("karan"));

    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "2, 3, 4",
            "2, 2, 4"
    })
    public void tests(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
