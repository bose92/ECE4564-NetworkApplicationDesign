package edu.vt.ece4564.project2;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class MyReviewServlet extends HttpServlet {
	ArrayList <String> finalQuery = new ArrayList<String> ();

	public static void main(String[] args) throws Exception {
		Server server = new Server(8085);
		WebAppContext context = new WebAppContext();
		context.setWar("war");
		context.setContextPath("/review");
		server.setHandler(context);

		server.start();
		server.join();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		if (req.getQueryString() != null && !(req.getQueryString().equals("")) && !(finalQuery.contains(req.getQueryString()))) {
			finalQuery.add(req.getQueryString());
		}
		for (int i = 0; i<finalQuery.size(); i++) {
		out.println("Room " + i + ": " + finalQuery.get(i).replace("%20", " ") + " " + "\n");
		}
		out.close();
	}
}
