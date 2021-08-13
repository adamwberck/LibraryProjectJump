package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Book;

public interface BookDao {
	
	public List<Book> getAllBooks();
	
	public List<Book> getAllCheckedOutBooks();
	
	public List<Book> getAllYourCheckedOutBooks(int patron_id);
	
	public List<Book> getHistory(int patronId);
	
	public Book getBookByIsbn(String isbn);
	
	public List<Book> getBooksByName(String title);
	
	public boolean addBook(Book book);
	
	public boolean takeOutBook(String isbn);
	
	public boolean returnBook(String isbn);
	
	public boolean updateBook(Book book);
	
	public boolean deleteBook(String isbn);
	
	

}