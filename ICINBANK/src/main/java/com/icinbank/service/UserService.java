package com.icinbank.service;

import com.icinbank.model.User;

public interface UserService {
	User findByEmail(String email);
	User findByUsername(String username);

}
