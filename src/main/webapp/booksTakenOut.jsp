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
						<a href="returnBook?id=<c:out value='${ book.isbn }' />">
						
							<button class="btn btn-danger">Return</button>
							
						</a>
						
					</td>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>

</div>