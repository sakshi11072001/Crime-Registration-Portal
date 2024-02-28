package com.Signup.Signup.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Signup.Signup.models.UserModel;

public interface MyRepo extends JpaRepository<UserModel,Integer> {

	Optional<UserModel> findByLoginAndPassword(String login, String password);
	
}
