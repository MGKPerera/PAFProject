package services;


import model.User;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/users")
public class Userervice {
	
	User userObj = new User(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return userObj.readUser();
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertuser( 
	 @FormParam("userName") String userName, 
	 @FormParam("email") String email, 
	 @FormParam("password") String password,
     @FormParam("address") String address)
	{ 
	 String output = userObj.insertUser(userName, email, password,address); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateuser(String userData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String userID = userObject.get("userID").getAsString(); 
	 String userName = userObject.get("userName").getAsString(); 
	 String email = userObject.get("email").getAsString(); 
	 String password = userObject.get("password").getAsString(); 
	 String address = userObject.get("address").getAsString(); 
	 String output = userObj.updateUser(userID, userName, email, password, address); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteuser(String userData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String userID = doc.select("userID").text(); 
	 String output = userObj.deleteUser(userID); 
	return output; 
	}

}