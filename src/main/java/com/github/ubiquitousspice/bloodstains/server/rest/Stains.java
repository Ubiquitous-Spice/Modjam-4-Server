package com.github.ubiquitousspice.bloodstains.server.rest;


import com.github.ubiquitousspice.bloodstains.server.Main;
import com.google.gson.JsonParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;

@Path("/{id}")
public class Stains
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getStains(@PathParam("id") String id)
	{
		Collection<String> stains = Main.worldStainMap.get(id);

		StringBuilder builder = new StringBuilder("{");
		for (String stain : stains)
		{
			builder.append(stain);
			builder.append(",");
		}
		builder.append("}");
		return builder.toString();
	}

	@PUT
	public void putStain(@PathParam("id") String id, String stain) throws IOException
	{

		new JsonParser().parse(stain);

		synchronized (Main.worldStainMap)
		{
			Main.worldStainMap.put(id, stain);
		}
	}
}
