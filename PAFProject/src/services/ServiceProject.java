package com;

import model.Project;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

//service Project
@Path("/Project")
public class ServiceProject {
	Project ProjectObj = new Project();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProject() {
		return ProjectObj.readProject();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("pName") String pName, 
			@FormParam("pDate") String pDate,
			@FormParam("pCate") String pCate,
			@FormParam("pDetai") String pDetai) {
		String output = ProjectObj.insertProject(pName, pDate, pCate, pDetai);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateProject(String productData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(productData).getAsJsonObject();

		// Read the values from the JSON object
		String pId = ProObject.get("pId").getAsString();
		String pName = ProObject.get("pName").getAsString();
		String pDate = ProObject.get("pDate").getAsString();
		String pCate = ProObject.get("pCate").getAsString();
		String pDetai = ProObject.get("pDetai").getAsString();

		String output = ProjectObj.updateProject(pId, pName, pDate, pCate, pDetai);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String productData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

		// Read the value from the element
		String pId = doc.select("pId").text();
		String output = ProjectObj.deleteProject(pId);
		return output;
	}
}
