package com.Signup.Signup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Signup.Signup.models.UserModel;
import com.Signup.Signup.repositories.MyRepo;

@Service
public class MyService {
	
	@Autowired
	private final MyRepo myRepo;
	

	public MyService(MyRepo myRepo) {
		this.myRepo = myRepo;
	}
	
	
	
	public UserModel registerUser(String login, String password, String email) {
		if(login != null && password != null) {
			UserModel userModel = new UserModel();
			userModel.setLogin(login);
			userModel.setPassword(password);
			userModel.setEmail(email);
			return myRepo.save(userModel);
		}else {
		
		return null;
	}
	}
	
	public UserModel authentication(String login, String password) {
		return myRepo.findByLoginAndPassword(login,password).orElse(null);
	}
	
	
}
