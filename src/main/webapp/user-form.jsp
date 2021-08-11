
<%@ include file="header.jsp" %>

<div class="container">

	<% String formType = "update"; %>


	<c:if test="${ user != null }">
	
		<h1>Update User</h1>
		
	</c:if>
	
	<c:if test = "${ user == null }">
		
		<h1>Create new user</h1>
		
		<% formType = "add"; %>
		
	</c:if>
	
	
	<form action="<%= formType %>" method="get" >
	
		<c:if test="${ user != null }">
		
			<%-- hidden input, won't show up on the page, but will pass our id for the product --%>
			<input type="hidden" name="id" value="<c:out value='${ user.id }' />">
		
		</c:if>
		
	  <div class="form-group">
	    
	    <label for="firstName">First Name</label>
	    <input type="text" class="form-control" id="firstName" name="firstName" 
	    	value="<c:out value='${ user.firstName }' />" required>
	    
	  </div>
	  
	   <div class="form-group">
	    
	    <label for="lastName">Last Name</label>
	    <input type="text" class="form-control" id="lastName" name="lastName" 
	    	value="<c:out value='${ user.lastName }' />" required>
	    
	  </div>
	  
	  
	  <div class="form-group">
	    
	    <label for="username">User name</label>
	    <input type="text" class="form-control" id="username" name="username" 
	    	value="<c:out value='${ user.username }' />" required>
	    
	  </div>
	  
	   <div class="form-group">
	    
	    <label for="password">Password</label>
	    <input type="text" class="form-control" id="password" name="password" 
	    	value="<c:out value='${ user.password }' />" required>
	    
	  </div>
	  

	  
	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px" >Submit</button>
	  
	</form>

</div>