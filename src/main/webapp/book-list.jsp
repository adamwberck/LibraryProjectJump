<%boolean user = false; %>
<% try {System.out.println(session.getAttribute("loggedIn").toString());
	if(session.getAttribute("loggedIn").toString()=="true"){user = true;}
	System.out.println(session.getAttribute("loggedIn").toString());}
catch(Exception e){
e.printStackTrace();}%>
<%@ include file="header.jsp" %>

<div class="container">
	
	<h1>Book List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>Isbn</th>
				<th>Title</th>
				<th>Description</th>
				<th>Actions</th>
			</tr>
		</thead>
		
		<tbody class="overflow-auto">

			<c:forEach var="book" items="${ allBooks }">
				
				<tr>
					<td>
						<c:out value="${ book.isbn }" />
					</td>
					
					<td>
						<c:out value="${ book.title }" />
					</td>
					
					<td>
						<c:out value="${ book.descr }" />
					</td>
					
					<td>
						
						<a href="goToUpdateBookForm?isbn=<c:out value='${ book.isbn }' />">
							<button class="btn btn-primary">Edit</button>
						</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						
					</td>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	
	<form action =" ./searchForBook" method= "GET">
		Search By Book Name: <input type= "text" name="value"/>
		<input type = "submit" value= "Search"/>
	</form>
	

</div>


<%@ include file="footer.jsp" %>
