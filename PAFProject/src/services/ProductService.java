package services;

import model.Product;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/products")
public class ProductService {
	
	
	Product productObj = new Product(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return productObj.readProducts();
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct(
	 @FormParam("productCode") String productCode, 
	 @FormParam("productName") String productName, 
	 @FormParam("productPrice") String productPrice, 
	 @FormParam("productDesc") String productDesc) 
	{ 
	 String output = productObj.insertProducts(productCode, productName, productPrice, productDesc); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String ProductData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject productObject = new JsonParser().parse(ProductData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String productID = productObject.get("productID").getAsString(); 
	 String productCode = productObject.get("productCode").getAsString(); 
	 String productName = productObject.get("productName").getAsString(); 
	 String productPrice = productObject.get("productPrice").getAsString(); 
	 String productDesc = productObject.get("productDesc").getAsString(); 
	 String output = productObj.updateProducts(productID, productCode, productName, productPrice, productDesc); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String ProductData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(ProductData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String productID = doc.select("productID").text(); 
	 String output = productObj.deleteProducts(productID); 
	return output; 
	}
	
}
