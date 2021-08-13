
<%@ include file="header.jsp" %>

<div class="container">
	
	<h2>Welcome to the Library</h2>
	
		<c:if test="${ user.user}">
	
		<p>Welcome</p>
			
	</c:if>
	
	<c:if test = "${ !(user.user) }">
		<h1>${ !(user.user) }</h1>
		<h4 class="text-center" >Please Login</h4>
		<a class="nav-link" href="./login.jsp">Login</a> 
		
	</c:if>

	
</div>


<%@ include file="footer.jsp" %>
