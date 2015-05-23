package com.mechame.restws.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("ftocservice")
public class FtoCService {

	@GET
	@Path("OlaMundo/{p}")
    @Produces("text/plain")
    public String olaMundo(@PathParam("p") String p) {
		
		String nome = (p == null) ? "Fulano" : p;
		
        return String.valueOf("Ola Mundo "+nome+"!");
    }
	
	@GET
	@Produces("application/json")
	public Response convertFtoC() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		Double fahrenheit = 98.24;
		Double celsius;
		celsius = (fahrenheit - 32) * 5 / 9;
		jsonObject.put("F Value", fahrenheit);
		jsonObject.put("C Value", celsius);

		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@Path("{f}")
	@GET
	@Produces("application/json")
	public Response convertFtoCfromInput(@PathParam("f") float f)
			throws JSONException {

		JSONObject jsonObject = new JSONObject();
		float celsius;
		celsius = (f - 32) * 5 / 9;
		jsonObject.put("F Value", f);
		jsonObject.put("C Value", celsius);

		return Response.status(200).entity(jsonObject.toString()).build();
	}
}
