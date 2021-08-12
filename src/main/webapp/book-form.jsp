
<%@ include file="header.jsp" %>

<div class="container">

	<% String formType = "update"; %>


	<c:if test="${ book != null }">
	
		<h1>Update Book</h1>
		
	</c:if>
	
	<c:if test = "${ product == null }">
		
		<h1>Add New Book</h1>
		
		<% formType = "add"; %>
		
	</c:if>
	
	
	<form action="<%= formType %>" method="get" >
	
		<c:if test="${ book != null }">
		
			<%-- hidden input, won't show up on the page, but will pass our id for the product --%>
			<input type="hidden" name="id" value="<c:out value='${ book.isbn }' />">
		
		</c:if>
		
	  <div class="form-group">
	    
	    <label for="title">Title</label>
	    <input type="text" class="form-control" id="title" name="title" 
	    	value="<c:out value='${ book.title }' />" required>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="descr">Description</label>
	    <input type="text" class="form-control" id="descr" name="descr"
	    	value="<c:out value='${ book.descr }' />" required>
	    
	  </div>

	  
	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px" >Submit</button>
	  
	</form>

</div>
<%@ include file="footer.jsp" %>