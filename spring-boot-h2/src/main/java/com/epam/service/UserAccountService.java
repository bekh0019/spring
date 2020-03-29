package com.epam.service;

import com.epam.model.NumberChangeForm;
import com.epam.model.UserAccount;

public interface UserAccountService {
    void updateMobileOperator(NumberChangeForm form);

    UserAccount save(UserAccount userAccount);
}
