package com.epam.controller;

import com.epam.constant.Constants;
import com.epam.model.Phone;
import com.epam.model.PhoneCompany;
import com.epam.model.User;
import com.epam.service.PhoneCompanyService;
import com.epam.service.PhoneService;
import com.epam.service.UserService;
import com.epam.util.GeneratePDFUtils;
import com.sun.istack.internal.Nullable;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
public class EntityController {
    private UserService userService;

    public EntityController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneCompanyService phoneCompanyService;


    @GetMapping("/users")
    public ModelAndView getUsersPage() {
        return new ModelAndView("userList", "users", userService.getUsers());
    }

    @GetMapping(value = "/users", params = {"num", "ord"})
    public ModelAndView getUsersInOrder(@RequestParam("num") int userNumber,
                                        @Nullable @RequestParam("ord") String sortOrder) {
        if (Constants.ORDER_ASC.equals(sortOrder)) {
            return new ModelAndView("userList", "users",
                    userService.findUsersOrderByIdAsc(userNumber));
        }
        return new ModelAndView("userList", "users",
                userService.findUsersOrderByIdDesc(userNumber));
    }

    @GetMapping("/listPhone")
    public ResponseEntity<List<Phone>> phoneList() {
        List<Phone> phones = phoneService.getPhones();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @GetMapping("/listCompanies")
    public ResponseEntity<List<PhoneCompany>> phoneCompany() {
        List<PhoneCompany> companies = phoneCompanyService.getCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping(value = "/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport(@RequestHeader(value = "Accept", required = false) String accept) {
        if (StringUtils.isNullOrEmpty(accept) || !accept.contains("pdf")) {
            throw new UnsupportedOperationException();
        }
        ByteArrayInputStream bis = GeneratePDFUtils.getUsersPDF(userService.getUsers());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/user/{id}")
    public @ResponseBody User getUserAsPdf(@PathVariable Long id,
                                           @RequestHeader(value = "Accept", required = false) String accept){
        return userService.getUserById(id).isPresent() ? userService.getUserById(id).get() : new User();
    }
}
