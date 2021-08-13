package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Patron;
import com.cognixia.jump.model.User;

public interface UserDao {

	public User getUser(String username, String password);
	public boolean updateUser(User user); 
	public boolean addUser(Patron patron);
	public boolean userExists(String username, String password);
	public List<Patron> getFrozenPatrons();
	public List<Patron> getAllPatrons();
	
	
	
}
