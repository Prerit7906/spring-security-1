package com.spring_security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring_security.models.Users;

@Repository
public interface UserDetailsRepo extends CrudRepository<Users, Integer> {

	Users findByUsername(String username);
}
