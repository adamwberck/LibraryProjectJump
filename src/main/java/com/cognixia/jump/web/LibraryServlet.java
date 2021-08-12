package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.dao.UserDaoImp;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;

@WebServlet("/")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDaoImp userDao;
	private BookDaoImp bookDao;
	
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		userDao = new UserDaoImp();
		bookDao = new BookDaoImp();
		
		
	}
	
	
	public void destroy() {
		// TODO Auto-generated method stub
		
		try {
			ConnectionManager.getConnection().close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getServletPath();
		
		System.out.println(action);
		
		switch(action) {
		case "/login":
			response.sendRedirect("./login.jsp");
			break;
		
			
		case "/listCheckoutBooks":
			listBooks(request,response, 2);
			break;
			
		case "/listBookHistory":
			listBooks(request,response,3);
			break;
		case "/listAllBooks":
			//go to library page
			listBooks(request, response,1);
			break;
		
		case "/searchForBook":
			// search for specified book
			// Open a new page 
			searchForBook(request,response);
			break;
		case "/addUser":
			// add a user to the database
			addUser(request,response);
			
			break;
		
		case "/addBook":
			// add a book to the database
			addBook(request,response);
			
			break;
			
		case "/udateBook":
			// updates books title, etc. 
			updateBook(request,response);
			break;
		case "/updateUser":
			// updates the user in whatever capacity username, password, name, freeze
			updateUser(request,response);
			break;
		default:
			//redirect the url: localhost:8080/library
			// display index.js page
			response.sendRedirect("/");
			break;
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void listBooks(HttpServletRequest request, HttpServletResponse response, int choice) 
			throws ServletException, IOException{
		List<Book> listOf = null;
		String redirector = "/login";
		
		switch(choice) {
		case 1:
			listOf = bookDao.getAllBooks();
			String attribute = "allBooks";
			//TODO add page redirect
			redirector = "book-list.jsp";
			
			request.setAttribute(attribute, listOf);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(redirector);
			dispatcher.forward(request,response);
			break;
		case 2:
			listOf = bookDao.getAllCheckedOutBooks();	
			response.sendRedirect(redirector);
			break;
		case 3:
			int id = Integer.parseInt(request.getParameter("id"));
			listOf = bookDao.getHistory(id);
			response.sendRedirect(redirector);
			break;
		}
	}
	
	private void searchForBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// TODO Create regex, implement getBookByName
		String incomingValue = request.getParameter("value");
		Book searchedBookById = null;
		List<Book> searchedByTitle = null;
		
		if(incomingValue.equals("Regex-Expression")){
			searchedBookById = bookDao.getBookByIsbn(incomingValue);
			request.setAttribute("searchedBook", searchedBookById);
		}else {
			searchedByTitle = bookDao.getBooksByName(incomingValue);
			request.setAttribute("searchedTitle", searchedByTitle);
		}
		
		RequestDispatcher dispatcher 
		= request.getRequestDispatcher("book-list.jsp");
		
		
		dispatcher.forward(request,response);
		
		
	
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		int id = 1;
		boolean freeze = true;
		
		// id, firstName, lastName, userName, password, accountfreeze
		userDao.addUser(new Patron(id, firstName, lastName, username, password, freeze));
		
		response.sendRedirect("/");
	}
	
	private void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String descr = request.getParameter("descr");
		long millis = System.currentTimeMillis();
		
		bookDao.addBook(new Book(isbn, title, new java.sql.Date(millis), descr));
		
		
		
		response.sendRedirect("book-list.jsp");
	}
	
	private void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String descr = request.getParameter("descr");
		
		Book updatedBook = bookDao.getBookByIsbn(isbn);
		updatedBook.setTitle(title);
		updatedBook.setDescr(descr);
		
		
		// TODO Check with Philip missing update book; pstmt.setString (3, book,getIsbn());
		bookDao.updateBook(updatedBook);
		
		
		// TODO Add Proper Redirect
		response.sendRedirect("book-list.jsp");
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
				int id = Integer.parseInt(request.getParameter("id"));
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String username = request.getParameter("userName");
				String password = request.getParameter("password");
				// TODO check with Philip if this is correct parameter 
				boolean accountFrozen = Boolean.parseBoolean(request.getParameter("frozen"));
				
				
				if(firstName != null) {
					userDao.updateUser(new Patron(id, firstName, lastName,username,
							password, accountFrozen));
				}else {
					userDao.updateUser(new Librarian(id, username, password));
				}
				
			
				
				response.sendRedirect("/");
				
			}
	
	


}
