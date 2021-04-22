package service;

import model.Fund;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/funds")
public class FundService {
	
	Fund fundObj = new Fund(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return fundObj.readFunds();
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(
	 @FormParam("fundCode") String fundCode, 
	 @FormParam("projectName") String projectName, 
	 @FormParam("fundAmount") String fundAmount, 
	 @FormParam("fundDesc") String fundDesc) 
	{ 
	 String output = fundObj.insertFunds(fundCode, projectName, fundAmount, fundDesc); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String FundData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject fundObject = new JsonParser().parse(FundData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String fundID = fundObject.get("fundID").getAsString(); 
	 String fundCode = fundObject.get("fundCode").getAsString(); 
	 String projectName = fundObject.get("projectName").getAsString(); 
	 String fundAmount = fundObject.get("fundAmount").getAsString(); 
	 String fundDesc = fundObject.get("fundDesc").getAsString(); 
	String output = fundObj.updateFunds(fundID, fundCode, projectName, fundAmount, fundDesc); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String FundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(FundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String fundID = doc.select("fundID").text(); 
	 String output = fundObj.deleteFunds(fundID); 
	return output; 
	}

}
