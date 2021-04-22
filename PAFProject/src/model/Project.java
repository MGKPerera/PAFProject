package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {

	// DB connection
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gadgetproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProject(String pName, String pDate, String pCate, String pDetai) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into projects1(`pId`, `pName`, `pDate`, `pCate`, `pDetai`)" + " values ( ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pName);
			preparedStmt.setString(3, pDate);
			preparedStmt.setString(4, pCate);
			preparedStmt.setString(5, pDetai);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Project.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Project ID</th><th>Project Name</th><th>Date</th><th>Category</th><th>Details</th></tr>";
			String query = "select * from projects1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String pId = Integer.toString(rs.getInt("pId"));
				String pName = rs.getString("pName");
				String pDate = rs.getString("pDate");
				String pCate = rs.getString("pCate");
				String pDetai = rs.getString("pDetai");

				output += "<tr><td>" + pId + "</td>";
				output += "<td>" + pName + "</td>";
				output += "<td>" + pDate + "</td>";
				output += "<td>" + pCate + "</td>";
				output += "<td>" + pDetai + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Project.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateProject(String pId, String pName, String pDate, String pCate, String pDetai) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE projects1 SET pName=?,pDate=?,pCate=?,pDetai=? WHERE pId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, pName);
			preparedStmt.setString(2, pDate);
			preparedStmt.setString(3, pCate);
			preparedStmt.setString(4, pDetai);
			preparedStmt.setInt(5, Integer.parseInt(pId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Project.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public String deleteProject(String pId) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from projects1 where pId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Project.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
