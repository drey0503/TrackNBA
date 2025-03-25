package com.example.tracknba;

import static org.junit.Assert.*;
import org.junit.Test;

public class LoginActivityTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void login() {
        LoginActivity.class.getClass().getMethods();

        assertEquals("gonzalo", "gonzalo");
    }
}