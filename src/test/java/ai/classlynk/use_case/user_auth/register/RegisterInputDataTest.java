package ai.classlynk.use_case.user_auth.register;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterInputDataTest {
    private RegisterInputData user;

    @BeforeEach
    void init() {
        user= new RegisterInputData("Paul", "password", "password");
    }
    @Test
    void getName() {
        assertEquals("Paul", user.getName());
    }
    @Test
    void getPw1() {
        assertEquals("password", user.getPw1());
    }

    @Test
    void getPw2() {
        assertEquals("password", user.getPw2());}
    }