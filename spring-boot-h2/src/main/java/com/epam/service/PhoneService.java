package com.epam.service;

import com.epam.model.Phone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    List<Phone> getPhones();

    void savePhones(List<Phone> phones);

    @Query("select * from phone where user.id = id")
    Optional<Phone> findByUserId(@Param("id") Long id);

    Phone save(Phone phone);
}
