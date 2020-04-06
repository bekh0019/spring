package com.epam.service.currentuser;

import com.epam.model.CurrentUser;
import com.epam.model.Role;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.BOOKING_MANAGER || currentUser.getId().equals(userId));
    }

}
