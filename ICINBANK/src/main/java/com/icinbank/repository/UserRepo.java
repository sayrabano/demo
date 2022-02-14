package com.icinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.icinbank.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByMailid(String mailid);

	User findByFullname(String name);
}
