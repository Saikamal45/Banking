package com.banking.User_Service.Service;

import java.util.Optional;
import java.util.UUID;

import com.banking.User_Service.entity.User;
import com.banking.User_Service.exception.RoleNotFound;
import com.banking.User_Service.exception.UserNotFound;

public interface UserService {

	void addRoles();
	
	User addUser(String role,User user) throws RoleNotFound;
	
	User getUser(String email) throws UserNotFound;
	
	User updateProfile(int id,User user) throws UserNotFound;
	
	String deleteProfile(int id) ;
	
	Optional<User> getUserById(int id)throws UserNotFound;
}
