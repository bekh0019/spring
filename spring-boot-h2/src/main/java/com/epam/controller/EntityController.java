package com.epam.controller;

import com.epam.model.Phone;
import com.epam.model.PhoneCompany;
import com.epam.service.PhoneCompanyService;
import com.epam.service.PhoneService;
import com.epam.service.UserService;
import com.epam.util.GeneratePDFUtils;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
public class EntityController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneCompanyService phoneCompanyService;


    @GetMapping("/users")
    public ModelAndView getUsersPage() {
        return new ModelAndView("users", "users", userService.getUsers());
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

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport(@RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        if (StringUtils.isNullOrEmpty(accept) || !accept.contains("pdf")) {
            throw new Exception();
        }
        ByteArrayInputStream bis = GeneratePDFUtils.getUsersPDF(userService.getUsers());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
