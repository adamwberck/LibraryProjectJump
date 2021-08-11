package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Patron;
import com.cognixia.jump.model.User;

public interface UserDao {

	public boolean updateUser(User user); 
	public List<Patron> getFrozenPatrons();
	public List<Patron> getAllPatrons();
	
	
	
}
