package com.banking.accountService.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.banking.accountService.dto.UserDto;

@FeignClient(name="user-service")
public interface UserClient {

	@GetMapping("/user/getUserById/{id}")
	UserDto getUserById(@PathVariable int id);
}
