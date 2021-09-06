package com.somshine.userServer.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.somshine.userServer.model.User;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	List<User> findUserByAccessTypeId(Integer accessTypeId);
	long countByAccessTypeId(Integer accessTypeId);
	List<User> getUserByAccessTypeIdAndUsername(Integer accessTypeId, String username);
	List<User> getUserByAccessTypeIdAndMobileNo(Integer accessTypeId, String mobileNo);
	List<User> getUserByAccessTypeIdAndMobileNoOrUsername(Integer accessTypeId, String mobileNo, String username);
	
	List<User> findUserDistinctByFirstNameOrderByFirstNameAsc(String firstName);
	List<User> findDistinctUserByFirstNameOrderByFirstNameDesc(String firstName);
	
	List<User> findUserDistinctByFirstNameOrderByMobileNoAsc(String firstName);
	List<User> findDistinctUserByFirstNameOrderByMobileNoDesc(String firstName);
	
	List<User> findUserByFirstNameIgnoreCase(String firstName);
	List<User> findUserByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
	
	//Limiting Queries
	User findFirstByOrderByLastNameAsc();
	User findTopByOrderByFirstNameAsc();
	
//	Page<User> queryFirst10ByFirstName(String firstName, Pageable pageable);
//	Page<User> queryTop3ByFirstName(String firstName, Pageable pageable);
	
//	List<User> findFirst10ByFirstName(String firstName, Sort sort);
//	List<User> findTop10ByFirstName(String firstName, Pageable pageable);
	
	//Like query on string data
	Streamable<User> findByFirstNameContaining(String firstName);
	Streamable<User> findByFirstNameAndLastNameContaining(String firstName, String lastName);
}