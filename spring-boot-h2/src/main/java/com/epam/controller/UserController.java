package com.epam.controller;

import com.epam.model.UserCreateForm;
import com.epam.model.validator.UserCreateFormValidator;
import com.epam.service.PhoneCompanyService;
import com.epam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

import static com.epam.constant.Constants.*;

@Controller
public class UserController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @Autowired
    PhoneCompanyService phoneCompanyService;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @GetMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("Getting user page for user={}", id);
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")
    @GetMapping("/user/create")
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView(USER_CREATE, "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")
    @PostMapping("/user/create")
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            return USER_CREATE;
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return USER_CREATE;
        }
        return "redirect:/users";
    }
}
