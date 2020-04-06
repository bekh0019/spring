package com.epam.controller;


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    MockMvc mockMvc;

    HomeController controller = new HomeController();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void getHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

}