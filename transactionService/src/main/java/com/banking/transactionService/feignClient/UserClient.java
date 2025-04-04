package com.banking.transactionService.feignClient;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.banking.transactionService.dto.User;

@FeignClient(name="user-service",path="/user")
public interface UserClient {

	@GetMapping("/getUserById/{id}")
	Optional<User> getUserById(@PathVariable int id);
}
