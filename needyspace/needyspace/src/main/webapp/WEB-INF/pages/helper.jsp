<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: black;
}

* {
  box-sizing: border-box;
}

/* Add padding to containers */
.container {
  padding: 16px;
  background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=text] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=text]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity: 1;
}

/* Add a blue text color to links */
a {
  color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  background-color: #f1f1f1;
  text-align: center;
}
</style>
</head>
<body>
<h1 style="color: white"> NEEDY SPACE</h1>
<form action="/product/add" method="post" enctype="multipart/form-data">
<c:if test="${isMsgAvailable}">
	<h2 style="color: red">${message}</h2>
	</c:if>
  <div class="container">
    <h1>Thanks for your support to needy</h1>
    <p>Please fill in this form with product details</p>
    <hr>

    <label for="pname"><b>Product Name</b></label>
    <input type="text" placeholder="product name" name="product Name" id="pname" required>

    <label for="quality"><b>Quality</b></label>
    <input type="text" placeholder="quality" name="quality" id="quality" required>

    <label for="quantity"><b>Quantity</b></label>
    <input type="text" placeholder="quantity" name="quantity" id="quantity" required>
    
        <label for="description"><b>Description</b></label>
    <input type="text" placeholder="description" name="description" id="description" required>
    
        <label for="mobileNo"><b>Mobile No</b></label>
    <input type="text" placeholder="mobile no" name="mobileNo" id="mobileNo" required>
    
        <label for="picture"><b>Picture</b></label>
     <input type="file" name="picture" accept="image/png, image/jpeg" /></br>
    <p style="color: red">Max file size is 500kb</p></br>
        <label for="mailId"><b>Mail ID</b></label>
    <input type="text" placeholder="mail Id" name="mailId" id="mailId" required>
    
        <label for="cityName"><b>City</b></label>
    <input type="text" placeholder="city name" name="cityName" id="cityName" required>
    <hr>

    <button type="submit" class="registerbtn">Post</button>
  </div>
  
</form>

</body>
</html>