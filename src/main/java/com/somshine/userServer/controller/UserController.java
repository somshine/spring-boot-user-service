package com.somshine.userServer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.somshine.userServer.UserServiceApplication;
import com.somshine.userServer.model.User;
import com.somshine.userServer.repository.UserRepository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@ApiModel(description="Order REST API interface")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserServiceApplication.class.getName());
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/user/v1/get/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find Order by id", notes = "Return the Order Entity object.")
	public User getOrder(@PathVariable Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	@RequestMapping(value = "/user/v1/getUsersByTypeId/{typeId}", method = RequestMethod.GET)
	@ApiOperation(value = "Find Orders by Customer Id", notes = "It's will return the all orders associated to the customers.")
	public List<User> getOrderByCustomerId(@PathVariable int typeId) {
		return userRepository.findUserByAccessTypeId(typeId);
	}
	
	@RequestMapping(value = "/user/v1/list", method = RequestMethod.GET)
	public List<User> getOrders() {
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/user/v1/login/{username}", method = RequestMethod.POST)
	public User doLogin(@PathVariable String username, @RequestBody User user) {
		LOG.log(Level.INFO, "product/get/\n" + user.getUsername() + " is calling" + user.toString());
		return userRepository.findByUsername(username);
	}
	
	@RequestMapping(value = "/user/v1/getUsersByMobileNos", method = RequestMethod.GET)
	public List<User> getUsersByMobileNos(@RequestParam(value = "mobileNo", required = false) List<String> mobileNos) {
		LOG.log(Level.INFO, "user/v1/getUserByMobileNos\n");
		List<User> users = new ArrayList();
		for (String mobileNo : mobileNos) {
			User user = new User();
			user.setMobileNo(mobileNo);
			user.setId(Math.round(Math.random()*100));
			users.add(user);
		}
		return users;
	}
	
	@RequestMapping(value = "/user/v1/getUserByFilters", method = RequestMethod.GET)
	public List<User> getUserByFilters(
			@RequestParam(value = "accessTypeId", required = true) Integer accessTypeId,
			@RequestParam(value = "mobileNo", required = false) String mobileNo,
			@RequestParam(value = "username", required = false) String username
	) {
		LOG.log(Level.INFO, "user/v1/getUserByMobileNos\n");
		if (mobileNo != null && username != null && !mobileNo.isEmpty() && !username.isEmpty()) {
			return userRepository.getUserByAccessTypeIdAndMobileNoOrUsername(accessTypeId, mobileNo, username);
		}  else if (mobileNo != null && !mobileNo.isEmpty()) {
			return userRepository.getUserByAccessTypeIdAndMobileNo(accessTypeId, mobileNo);
		} else if (username != null && !username.isEmpty()) {
			return userRepository.getUserByAccessTypeIdAndUsername(accessTypeId, username);
		}
		
		return null;
	}
	
	@RequestMapping(value = "/user/v1/getUserByFirstNameSort/{firstName}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	public List<User> getUserByFirstNameSort(@PathVariable String firstName, @RequestParam(value = "sorting", required = false) String sort) {
		LOG.log(Level.INFO, "user/v1/getUserByFirstNameSort/{firstName}\n");
		if (firstName != null && !firstName.isEmpty()) {
			if ((sort == null || sort.equalsIgnoreCase("asc"))) {
				return userRepository.findUserDistinctByFirstNameOrderByMobileNoAsc(firstName);
			} else {
				return userRepository.findDistinctUserByFirstNameOrderByMobileNoDesc(firstName);
			}
		}
		
		return null;
	}
	
	@RequestMapping(value = "/user/v1/add", method = RequestMethod.POST)
	public User addNewUser(@RequestBody User user) {
		System.err.println(user.toString());
		return user;
	}
}
