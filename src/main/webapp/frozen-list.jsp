<div class="container">

	
	<h1>Frozen Users List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Actions</th>
			</tr>
		</thead>
		
		<tbody class="overflow-auto">

			<c:forEach var="user" items="${ listOf }">
				
				<tr>
					<td>
						<c:out value="${ user.patron_id }" />
					</td>
					
					<td>
						<c:out value="${ user.firstName }" />
					</td>
					
					<td>
						<c:out value="${ user.lastName }" />
					</td>
					
					<td>
						<c:out value="${ user.userame }"  />
					</td>
					
					
					<td>
			
						<a href="unfreezeUser?id=<c:out value='${ user.id }' />">
						
							<button class="btn btn-danger">unfreeze</button>
							
						</a>
						
					</td>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	


</div>