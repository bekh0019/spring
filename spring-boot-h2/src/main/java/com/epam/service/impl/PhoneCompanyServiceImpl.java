package com.epam.service.impl;

import com.epam.model.PhoneCompany;
import com.epam.repository.PhoneCompanyRepository;
import com.epam.service.PhoneCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PhoneCompanyServiceImpl implements PhoneCompanyService {
    @Autowired
    PhoneCompanyRepository phoneCompanyRepository;

    @Override
    public List<PhoneCompany> getCompanies() {
        List<PhoneCompany> companies = new ArrayList<>();
        phoneCompanyRepository.findAll().forEach(companies::add);
        return companies;
    }

    @Override
    public void savePhoneCompanies(List<PhoneCompany> companies) {
        companies.forEach(phoneCompanyRepository::save);
    }

    @Override
    public PhoneCompany findByName(String name) {
        return phoneCompanyRepository.findByName(name);
    }
}
