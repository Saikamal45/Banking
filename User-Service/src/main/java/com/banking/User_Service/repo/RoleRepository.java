package com.banking.User_Service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banking.User_Service.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String>{

}
