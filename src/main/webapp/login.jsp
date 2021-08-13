<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Library</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">

</head>
<body>
	
	<div class="container-fluid">

		<h1 class="text-center">Welcome to the Library</h1>
         
       <div class="container login-container">
            <div class="row">
                <div class="col-md-6 login-form-1">
                    <h3>Login</h3>
                    <form action="./loginUser" method="get">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Username *" value="" name="username" />
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Password *" value="" name="password"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" value="Login" />
                        </div>
                    </form>
                </div>
                <div class="col-md-6 login-form-2">
                    <h3>Sign up</h3>
                    <form action="./addUser" method="get">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Username *" value="" name="username"/>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Password *" value="" name="password" />
                        </div>
                         <div class="form-group">
                            <input type="text" class="form-control" placeholder="First Name" value="" name="firstName" />
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Last Name" value=""name="lastName" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" value="Sign Up" />
                        </div>
                    </form>
                </div>
            </div>
        </div>

<%@ include file="footer.jsp" %>