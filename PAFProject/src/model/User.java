package model;

import java.sql.*;

public class User {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	
	public String insertUser( String userName, String email, String password, String address) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into user (`userID`,`userName`,`email`,`password`,`address`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, userName); 
	 preparedStmt.setString(3, email); 
	 preparedStmt.setString(4, password); 
	 preparedStmt.setString(5, address); 
 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the User."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	public String readUser() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>userID</th>" +
	 "<th>userName</th>" + 
	 "<th>email</th>" +
     "<th>password</th>"+
      "<th>address</th>"+
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from user"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String userID = Integer.toString(rs.getInt("userID"));  
	 String userName = rs.getString("userName"); 
	 String email = rs.getString("email"); 
	 String password = rs.getString("password"); 
	 String address = rs.getString("address"); 
	 // Add into the html table
	 output += "<tr><td>" + userID + "</td>"; 
	 output += "<td>" + userName + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + password + "</td>"; 
     output += "<td>" + address + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='UserID' type='hidden' value='" + userID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the user."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateUser(String userID, String userName, String email, String password, String address)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE user SET userName=?,email=?,password=?,address=? WHERE userID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
	 preparedStmt.setString(1, userName); 
	 preparedStmt.setString(2, email); 
	 preparedStmt.setString(3, password); 
	 preparedStmt.setString(4, address);
	 preparedStmt.setInt(5, Integer.parseInt(userID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the User."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
	
	public String deleteUser(String userID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from user where userID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(userID));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the User."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
