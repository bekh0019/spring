package com.epam.repository;

import com.epam.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByEmail(String email);
}
