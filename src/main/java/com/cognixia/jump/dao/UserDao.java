package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Patron;

public class UserDao {

	
	public static final Connection conn = ConnectionManager.getConnection();
	// List Frozen Patrons 
	private static String SELECT_ALL_FROZEN_PATRONS =  
			"select * from patron where account_frozen = true;";
	
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
				
				frozenPatrons.add(id, firstName, lastName, userName, password,
						frozen);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return null;
		
	}
}
