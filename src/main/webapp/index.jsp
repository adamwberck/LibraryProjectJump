
<%boolean user = false; %>
<% try {System.out.println(session.getAttribute("loggedIn").toString());
	if(session.getAttribute("loggedIn").toString()=="true"){user = true;}
	System.out.println(session.getAttribute("loggedIn").toString());}
catch(Exception e){
e.printStackTrace();}%>


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
		<c:if test= "${patron != null}">
			Test Connection
			
		</c:if>

		<%@ include file="booksTakenOut.jsp" %>
		</div>
	
			
	</c:if>
	
	
	<c:if test="<%= !user %>">
		<h2 class="text-center">Welcome to the Library</h2>
		<h4 >Please Login</h4>
		<a class="nav-link" href="./login.jsp">Login</a> 
		
	</c:if>

	
</div>


<%@ include file="footer.jsp" %>
