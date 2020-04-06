package com.epam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String getHomePage(HttpServletRequest request) {
        LOGGER.debug("Getting home page");
        return "home";
    }

}
