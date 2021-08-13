
<% boolean user = true; %>
<%@ include file="header.jsp" %>

<div class="container">

		<c:if test="<%= user %>">
	
		<h2>Welcome to the Library</h2>
		
		<h3>Would you like to search for a book?</h3>
			<form action =" ./searchForBook" method= "GET">
			Search By Book Name: <input type= "text" name="value"/>
			<input type = "submit" value= "Search"/>
			</form>
		<div class="container">
		<h3>Heres your info</h3>
		
		</div>
	
			
	</c:if>
	
	
	<c:if test="<%= !user %>">
		<h2 class="text-center">Welcome to the Library</h2>
		<h4 >Please Login</h4>
		<a class="nav-link" href="./login.jsp">Login</a> 
		
	</c:if>

	
</div>


<%@ include file="footer.jsp" %>
