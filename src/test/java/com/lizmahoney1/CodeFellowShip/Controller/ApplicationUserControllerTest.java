package com.lizmahoney1.CodeFellowShip.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationUserControllerTest {

    @Autowired
    ApplicationUserController controller;

    @Autowired
    MockMvc mockMvc;

    // Test index.html
    @Test
    public void getRoot() throws Exception {
        ApplicationUserController controller = new ApplicationUserController();
        mockMvc.perform(get("/")).andExpect(content().string(containsString("Welcome")));
    }

    // Test login page
    @Test
    public void testRequestLogin() throws Exception {
        mockMvc.perform(get("/login")).andExpect(content().string(containsString("Username")));
    }

    //Test signup page
    @Test
    public void testRequestSignupPage() throws Exception {
        mockMvc.perform(get("/signup")).andExpect(content().string(containsString("Bio")));
    }
}