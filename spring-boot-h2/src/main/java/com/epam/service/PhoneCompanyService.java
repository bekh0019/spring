package com.epam.service;

import com.epam.model.PhoneCompany;

import java.util.List;

public interface PhoneCompanyService {
    List<PhoneCompany> getCompanies();

    void savePhoneCompanies(List<PhoneCompany> companies);
}
