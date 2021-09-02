package com.somshine.userServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.somshine.userServer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	List<User> findUserByAccessTypeId(Integer accessTypeId);
	long countByAccessTypeId(Integer accessTypeId);
}
