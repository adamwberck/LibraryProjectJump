
<%@ include file="header.jsp" %>

<div class="container">

	<% String formType = "search"; %>

	
	<c:if test = "${ user == null }">
		
		<h1>Remember to login to take out a book</h1>
		
		<% formType = "NoRentSearch"; %>
		
	</c:if>
	
	
	<form action="<%= formType %>" method="get" >

		
	  <div class="form-group">
	    
	    <label for="title">Title</label>
	    <input type="text" class="form-control" id="title" name="title" 
	    	value="<c:out value=Text />" >
	    
	  </div>
	  
	   <div class="form-group">
	    
	    <label for="isbn">Isbn</label>
	    <input type="text" class="form-control" id="isbn" name="isbn" 
	    	value="<c:out value=Text />" >
	    
	  </div>

	  
	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px" >Submit</button>
	  
	</form>

</div>