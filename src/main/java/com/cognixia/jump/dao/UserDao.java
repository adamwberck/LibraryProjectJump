package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;
import com.cognixia.jump.model.User;

public class UserDao {

	
	public static final Connection conn = ConnectionManager.getConnection();
	// List Frozen Patrons 
	
	private static String SELECT_ALL_FROZEN_PATRONS =  
			"select * from patron where account_frozen = true;";

	
	private static String UPDATE_USER = "update";
	
	
	
	//  Update their name, username, and password
	
	public static void updateUser(User user) {
		
		Patron patron = null;
		Librarian librarian = null;
		
		
		if(Patron.class.isInstance(user)) {
			 patron = (Patron) user;
			UPDATE_USER += " patron set patron_id = ?,"
			 		+ "first_name=?, last_name=?,username=?,password=?,account_frozen=? where username";
			
		} else if(Librarian.class.isInstance(user)) {
			 librarian = (Librarian) user;
			 UPDATE_USER += "librarian set librarian_id =?, username =?, password=? where username = ?";
		
		}
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_USER);
				){
			
			if(patron !=null) {
				pstmt.setInt(1, patron.getId());
				pstmt.setString(2, patron.getFirstName());
				pstmt.setString(3, patron.getLastName());
				pstmt.setString(4, patron.getUsername());
				pstmt.setString(5, patron.getPassword());
				pstmt.setBoolean(6, patron.isAccountFrozen());
				pstmt.setString(7, patron.getUsername());
			}
			
			else if(librarian != null) {
				pstmt.setInt(1, librarian.getId());
				pstmt.setString(2, librarian.getUsername());
				pstmt.setString(3, librarian.getPassword());
				pstmt.setString(4, librarian.getUsername());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		}
		
	
	
	public static List<Patron> getFrozenPatrons(){
		
		List<Patron> frozenPatrons = new ArrayList<Patron>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_FROZEN_PATRONS);
				ResultSet rs = pstmt.executeQuery();) {
			
			
			while(rs.next()) {
				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");
				
				frozenPatrons.add(new Patron(id, firstName, lastName, userName, password,
						frozen));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return frozenPatrons;
		
	}

	
	
	
	
}
