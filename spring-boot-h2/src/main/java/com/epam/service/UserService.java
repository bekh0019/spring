/**
 * 
 */
package com.epam.service;

import java.util.List;

import com.epam.model.User;

public interface UserService {
	List<User> getUsers();

	void saveUsers(List<User> users);

}
