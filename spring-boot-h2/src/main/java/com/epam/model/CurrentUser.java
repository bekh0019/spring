package com.epam.model;

import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.Serializable;

@Data
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private transient User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }
}
