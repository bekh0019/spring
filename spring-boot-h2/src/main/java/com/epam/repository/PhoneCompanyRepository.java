package com.epam.repository;

import com.epam.model.PhoneCompany;
import org.springframework.data.repository.CrudRepository;

public interface PhoneCompanyRepository extends CrudRepository<PhoneCompany,Long> {
    PhoneCompany findByName(String name);
}