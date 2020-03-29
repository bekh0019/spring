package com.epam.service.impl;

import com.epam.model.Phone;
import com.epam.repository.PhoneRepository;
import com.epam.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    @Override
    public List<Phone> getPhones() {
        List<Phone> phones = new ArrayList<>();
        phoneRepository.findAll().forEach(phones::add);
        return phones;
    }

    @Override
    public void savePhones(List<Phone> phones) {
        phones.forEach(phoneRepository::save);
    }

    @Override
    public Optional<Phone> findByUserId(Long id) {
        return Optional.ofNullable(phoneRepository.findOne(id));
    }

    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }
}
