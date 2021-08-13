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

public class UserDaoImp implements UserDao {

	
	public static final Connection conn = ConnectionManager.getConnection();
	// List Frozen Patrons 
	
	private static String SELECT_ALL_FROZEN_PATRONS =  
			"select * from patron where account_frozen = true";
	
	private static String SELECT_FROZEN_PATRON =  
			"update patron set account_frozen = 0 where patron_id = ?";

	private static String SELECT_ALL_PATRONS = 
			"select * from patrons";
	
	private static String UPDATE_USER = "update";
	private static String INSERT_USER = "insert into patron "
			+ "(first_name, last_name, username, password, account_frozen) "
			+ "values( ?, ? , ?, ? , ?)";
	
	private static String PATRON_EXISTS = "select * from patron where username = ?  and password = ?";
	private static String LIB_EXISTS = "select * from librarian where username = ?  and password = ?";

	
	//  Update their name, username, and password
	@Override
	public boolean updateUser(User user) {
		
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
			
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
		}
		
	@Override
	public boolean addUser(Patron patron) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_USER)) {
			
			pstmt.setString(1, patron.getFirstName());
			pstmt.setString(2, patron.getLastName());
			pstmt.setString(3, patron.getUsername());
			pstmt.setString(4, patron.getPassword());
			pstmt.setBoolean(5, patron.isAccountFrozen());
			
			// at least one row added
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean unfreezeUser(int id) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_FROZEN_PATRON);) {
			
			pstmt.setInt(1, id);
			
			if(pstmt.execute()) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	@Override
	public boolean userExists(String username, String password) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(PATRON_EXISTS); PreparedStatement pstmt2 = conn.prepareStatement(LIB_EXISTS);) {
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt2.setString(1, username);
			pstmt2.setString(2, password);
			
			// at least one row added
			if(pstmt.execute()) {
				return true;
			}
			else if(pstmt2.execute()) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	public List<Patron> getFrozenPatrons(){
		
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
				
				frozenPatrons.add(new Patron( firstName, lastName, userName, password,
						frozen));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return frozenPatrons;
		
	}
	
public List<Patron> getAllPatrons(){
		
		List<Patron> frozenPatrons = new ArrayList<Patron>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_PATRONS);
				
				ResultSet rs = pstmt.executeQuery();) {
			
			
			while(rs.next()) {
				int id = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");
				
				frozenPatrons.add(new Patron( firstName, lastName, userName, password,
						frozen));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return frozenPatrons;
		
	}

@Override
public User getUser(String username, String password) {
	// TODO Auto-generated method stub
	try(PreparedStatement pstmt = conn.prepareStatement(PATRON_EXISTS);
			PreparedStatement pstmtLib = conn.prepareStatement(LIB_EXISTS);			) {
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		pstmtLib.setString(1, username);
		pstmtLib.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("Checking.......");
		while(rs.next()) {
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String userName = rs.getString("username");
			String returnedPassword = rs.getString("password");
			boolean frozen = rs.getBoolean("account_frozen");
			return new Patron( firstName, lastName, userName, returnedPassword,
					frozen);
			
			
		}
		
		ResultSet rs2 = pstmtLib.executeQuery();
		while(rs2.next()) {
			System.out.println("Query Accepted");
			int id = Integer.parseInt(rs2.getString("librarian_id"));
			String userName = rs2.getString("username");
			String returnedPassword = rs2.getString("password");	
			return new Librarian(id, userName, returnedPassword);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}

	
	
	
	
}
