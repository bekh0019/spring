package com.epam.service;

import com.epam.model.Phone;

import java.util.List;

public interface PhoneService {
    List<Phone> getPhones();

    void savePhones(List<Phone> phones);
}
