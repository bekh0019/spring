package com.epam.service;

import com.epam.model.PhoneCompany;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneCompanyService {
    List<PhoneCompany> getCompanies();

    void savePhoneCompanies(List<PhoneCompany> companies);

    PhoneCompany findByName(String name);
}
