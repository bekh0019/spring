/**
 *
 */
package com.epam.service;

import com.epam.model.User;
import com.epam.model.UserCreateForm;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    List<User> getUsers();

    void saveUsers(List<User> users);

    User create(UserCreateForm form);

    User save (User user);
}
