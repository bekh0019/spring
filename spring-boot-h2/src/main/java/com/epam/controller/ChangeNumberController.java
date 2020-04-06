package com.epam.controller;

import com.epam.model.NumberChangeForm;
import com.epam.service.PhoneCompanyService;
import com.epam.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChangeNumberController {
    @Autowired
    private PhoneCompanyService phoneCompanyService;

    @Autowired
    private UserAccountService userAccountService;

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @GetMapping("/user/{id}/changeOperator")
    public ModelAndView getChangeNumberPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("numberChangeForm", new NumberChangeForm());
        modelAndView.addObject("companies", phoneCompanyService.getCompanies());
        modelAndView.setViewName("changeNumber");
        return modelAndView;
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @PostMapping("/user/{id}/changeOperator")
    public ModelAndView handleChangeNumber(@PathVariable Long id, @ModelAttribute("form") NumberChangeForm form) {
        form.setUserId(id);
        userAccountService.updateMobileOperator(form);
        return new ModelAndView("redirect:/");
    }
}
