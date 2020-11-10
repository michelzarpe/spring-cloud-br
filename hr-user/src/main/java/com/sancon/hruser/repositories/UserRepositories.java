package com.sancon.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sancon.hruser.entities.User;

@Repository
public interface UserRepositories extends JpaRepository<User, Long>{

	public User findByEmail(String email);
}
