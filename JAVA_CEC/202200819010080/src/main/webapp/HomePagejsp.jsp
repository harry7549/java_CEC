<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
</head>
<body>
	<form action="">
		<table align="center" border="2" bgcolor="skyblue">
			<tr><td align="center" colspan="5" ><h3>Product Details</h3></td></tr>
			<tr>
				<td>ProductID</td>
				<td>ProductName</td>
				<td>ProductDescription</td>
				<td>ProductPrice</td>
				<td>Action</td>
			</tr>
			<%
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cec","root","");
					Statement st = con.createStatement();
					String query = "select * from productitem";
					ResultSet rs = st.executeQuery(query);
					
					while(rs.next()){
						%>
						<tr>
							<td><%= rs.getInt(1) %></td>
							<td><%= rs.getString(2) %></td>
							<td><%= rs.getString(3) %></td>
							<td><%= rs.getString(4) %></td>
							<td><a href="Update.jsp?id=<%= rs.getInt(1) %>">Update</a></td>
							<td><a href="deleteProduct?id=<%= rs.getInt(1) %>">Delete</a></td>
						</tr>
						<%
						
					}
					
				}
			catch(Exception e){
				e.printStackTrace();
			}
			%>
		</table>
		<p align="center">Add Product?<a href="AddProduct.html">ADD</a></p>
		<p align="center">Login?<a href="Login.html">Login</a></p>
	</form>
	


</body>
</html>