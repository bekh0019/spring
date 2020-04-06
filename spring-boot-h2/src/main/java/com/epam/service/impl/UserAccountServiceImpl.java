package com.epam.service.impl;

import com.epam.constant.Constants;
import com.epam.model.*;
import com.epam.repository.UserAccountRepository;
import com.epam.service.PhoneCompanyService;
import com.epam.service.PhoneService;
import com.epam.service.UserAccountService;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Transactional
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserService userService;

    @Autowired
    PhoneService phoneService;

    @Autowired
    PhoneCompanyService phoneCompanyService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public void updateMobileOperator(NumberChangeForm form) {
                Phone phone = new Phone();
                User user = new User();
                Optional<User> userOptional = userService.getUserById(form.getUserId());
                Optional<BigDecimal> userCash = userOptional.map(User::getUserCash);
                if (userCash.isPresent() &&
                        Constants.CHANGE_NUMBER_PRICE.subtract(userCash.get()).compareTo(BigDecimal.ZERO) > 0) {
                    throw new UnsupportedOperationException();
                }
                if (userOptional.isPresent() && userCash.isPresent()){
                    user = userOptional.get();
                    user.setUserCash(userCash.get().subtract(Constants.CHANGE_NUMBER_PRICE));
                }
                userService.save(user);
                final Optional<Phone> phoneByUserId = phoneService.findByUserId(form.getUserId());
                if(phoneByUserId.isPresent()){
                    phone = phoneByUserId.get();
                }
                PhoneCompany phoneCompany = phoneCompanyService.findByName(form.getCompanyName());
                phone.setNumber(form.getPhoneNumber());
                phone.setCompany(phoneCompany);
                phoneService.save(phone);
                UserAccount userAccount = userAccountRepository.findOne(form.getUserId());
                userAccount.setCompany(phoneCompany);
                userAccount.setPhone(phone);
                userAccountRepository.save(userAccount);
            }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
}
