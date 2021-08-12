
<%@ include file="header.jsp" %>

<div class="container">
	
	<h2>Welcome to the Library</h2>
	
		<c:if test="${ user != null }">
	
		<p>Welcome</p>
		
	</c:if>
	
	<c:if test = "${ user == null }">
		
		<h4>Please Login</h4>
		<a class="nav-link" href="./login">Login</a> 
		
	</c:if>

	
</div>


<%@ include file="footer.jsp" %>
