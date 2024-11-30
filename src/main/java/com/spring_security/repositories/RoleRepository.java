package com.spring_security.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring_security.models.AppRole;
import com.spring_security.models.Role;

@Repository
public interface RoleRepository  extends CrudRepository<Role, Integer>{

	Optional<Role> findByAppRole(AppRole appRole);
;

}
