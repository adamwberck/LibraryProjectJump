package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;

public class BookDaoImp implements BookDao {
	
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static String SELECT_ALL_BOOKS = "select * from book";
	private static String SELECT_ALL_CHECKED_BOOKS = "select * from book where rented = true";
	private static String SELECT_ALL_CHECKED_BOOKS_HISTORY = "select * from book_checkout where patron_id = ?";
	private static String SELECT_BOOK_BY_ID = "select * from book where isbn = ?";
	private static String SELECT_BOOKS_BY_NAME = "select * from book where title = ?";
	private static String INSERT_BOOK = "insert into book(isbn, title, descr, added_to_library) values(?, ?, ?,current_date())";
	private static String DELETE_BOOK = "delete from book where isbn = ?";
	private static String UPDATE_BOOK = "update book set title = ?,  descr = ? where isbn = ?";
	private static String TAKE_OUT_BOOK = "update book set rented = true where isbn = ?";

	
	@Override
	public List<Book> getAllBooks() {
		
		List<Book> allBooks = new ArrayList<Book>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BOOKS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next()) {
				
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Date addedToLibrary = rs.getDate("added_to_library");
				String description = rs.getString("descr");
				
				allBooks.add(new Book(isbn, title, addedToLibrary, description));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allBooks;
	}
	
	@Override
	public List<Book> getAllCheckedOutBooks() {
		
		List<Book> allCheckedBooks = new ArrayList<Book>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_CHECKED_BOOKS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next()) {
				
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Date addedToLibrary = rs.getDate("added_to_library");
				String description = rs.getString("descr");
				
				allCheckedBooks.add(new Book(isbn, title, addedToLibrary, description));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allCheckedBooks;
	}
	
	@Override
	public List<Book> getHistory(int patronId) {
		
		List<Book> history = new ArrayList<Book>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_CHECKED_BOOKS_HISTORY);
				ResultSet rs = pstmt.executeQuery() ) {
			pstmt.setInt(1, patronId);
			while(rs.next()) {
				
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Date addedToLibrary = rs.getDate("added_to_library");
				String description = rs.getString("descr");
				
				history.add(new Book(isbn, title, addedToLibrary, description));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return history;
	}

	
	@Override
	public Book getBookByIsbn(String isbn) {
		
		Book book = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOK_BY_ID)) {
			
			pstmt.setString(1, isbn);
			
			ResultSet rs = pstmt.executeQuery();
			
			// if product found, if statement run, if not null returned as product
			if(rs.next()) {
				String title = rs.getString("title");
				Date addedToLibrary = rs.getDate("added_to_library");
				String description = rs.getString("descr");
				
				book = new Book(isbn, title, addedToLibrary, description);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return book;
	}
	

	@Override
	public List<Book> getBooksByName(String titleSearched) {
		
		List<Book> booksOfTitle = new ArrayList<Book>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOKS_BY_NAME)){
				pstmt.setString(1, titleSearched);
				ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Date addedToLibrary = rs.getDate("added_to_library");
				String description = rs.getString("descr");
				
				booksOfTitle.add(new Book(isbn, title, addedToLibrary, description));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return booksOfTitle;
	}

	
	@Override
	public boolean addBook(Book book) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_BOOK)) {
			
			pstmt.setString(1, book.getIsbn());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getDescr());
			
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
	public boolean deleteBook(String isbn) {

		try (PreparedStatement pstmt = conn.prepareStatement(DELETE_BOOK)) {

			pstmt.setString(1, isbn);

			// at least one row deleted
			if (pstmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}


	@Override
	public boolean updateBook(Book book) {
		
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_BOOK)) {

			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getDescr());

			// at least one row updated
			if (pstmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean takeOutBook(String isbn) {
		
		try (PreparedStatement pstmt = conn.prepareStatement(TAKE_OUT_BOOK)) {

			pstmt.setString(1, isbn);

			// at least one row updated
			if (pstmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
