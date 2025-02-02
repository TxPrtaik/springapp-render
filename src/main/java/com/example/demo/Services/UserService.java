package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;

@Service
public class UserService {
@Autowired
private UserRepo ur;
public void saveUser(User u) {
	ur.save(u);
}
public int findUser(User u) {
	User us=ur.getUserByUsername(u.getUsername());
	
	if(us!=null) {
		if(us.getPassword().equals(u.getPassword())) {
			return us.getId();
		}
		else {
			return 0;
		}
	}
	else {
		return -1;
	}
}
}
