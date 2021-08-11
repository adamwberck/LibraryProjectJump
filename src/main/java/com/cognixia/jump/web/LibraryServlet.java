package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.dao.UserDaoImp;
import com.cognixia.jump.model.Book;

/**
 * Servlet implementation class LibrarySevelet
 */
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
			//go to login page
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
			break;
		
		case "/addBook":
			break;
			
		case "/udateBook":
			break;
		case "/updateUser":
			// updates the user in whatever capacity username, password, name, freeze
			break;
		default:
			//redirect the url: localhost:8080/CrudProject
			// display index.js page
			response.sendRedirect("/");
			break;
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void listBooks(HttpServletRequest request, HttpServletResponse response, int choice) 
			throws ServletException, IOException{
		List<Book> listOf = null;
		String attribute = "";
		String redirector = "/login";
		
		switch(choice) {
		case 1:
			listOf = bookDao.getAllBooks();
			attribute = "allBooks";
			//TODO add page redirect
			redirector = "some.jsp";
			
			request.setAttribute("allBooks", listOf);
			
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
		= request.getRequestDispatcher("some.jsp");
		//TODO add page redirect
		
		dispatcher.forward(request,response);
		
	}


}
