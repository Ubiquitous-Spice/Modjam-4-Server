package com.github.ubiquitousspice.bloodstains.server;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.UUID;

public class Main
{
	public static Multimap<String, String> worldStainMap = HashMultimap.create();

	public static void main(String[] args) {
		Server server = new Server(8080);
		ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		server.setHandler(handler);
		ServletHolder sh = new ServletHolder(ServletContainer.class);
		sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
		sh.setInitParameter("com.sun.jersey.config.property.packages", "com.github.ubiquitousspice.bloodstains.server.rest");//Set the package where the services reside
		handler.addServlet(sh, "/*");
		try
		{
			server.start();
			server.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
