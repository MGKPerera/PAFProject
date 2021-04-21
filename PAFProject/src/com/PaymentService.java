package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")

public class PaymentService {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("pid") String pid, 
			@FormParam("pmethod") String pmethod, 
			@FormParam("pdate") String pdate,
			@FormParam("pamount") String pamount) {
		String output = PaymentObj.insertPayment(pid, pmethod, pdate, pamount);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject PayObject = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String pid = PayObject.get("pid").getAsString();
		String pmethod = PayObject.get("pmethod").getAsString();
		String pdate = PayObject.get("pdate").getAsString();
		String pamount = PayObject.get("pamount").getAsString();
		
		String output = PaymentObj.updatePayment(pid, pmethod, pdate, pamount);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element
		String pid = doc.select("pid").text();
		String output = PaymentObj.deletePayment(pid);
		return output;
	}
}
