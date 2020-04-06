package com.epam.service.impl;

import com.epam.model.User;
import com.epam.model.UserCreateForm;
import com.epam.repository.UserRepository;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public void saveUsers(List<User> users) {
        users.forEach(userRepository::save);
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setUserCash(form.getUserCash());
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
      return userRepository.save(user);
    }

    @Override
    public Optional<List<User>> findUsersOrderByIdAsc(int number) {
      return   Optional.ofNullable(entityManager.createQuery("SELECT u FROM User u ORDER BY id ASC",
                User.class)
                .setMaxResults(number).getResultList());
    }

    @Override
    public Optional<List<User>> findUsersOrderByIdDesc(int number) {
        return Optional.ofNullable(entityManager.createQuery("SELECT u FROM User u ORDER BY id DESC",
                User.class)
                .setMaxResults(number).getResultList());
    }
}
