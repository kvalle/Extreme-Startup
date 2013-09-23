import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;
import org.simpleframework.http.Query;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

class Web implements Container {

	// Handles all HTTP request on this server
	public void handle(Request request, Response response) {
		try {
			String question = getQuestion(request);
			System.out.println("Q: " + question);
			answer(response, "Bendik");
		} catch (Exception e) { e.printStackTrace(); }
	}

	// Gets the question from the request parameter
	private String getQuestion(Request request) {
		return request.getQuery().get("q");
	}

	// Sends the answer as raw text to the client
	private void answer(Response response, String answer) throws Exception {
		System.out.println("A: " + answer);
		PrintStream body = response.getPrintStream();
		body.println("Hello");
		body.close();
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Hei");
		Container container = new Web();
		Server server = new ContainerServer(container);
		Connection connection = new SocketConnection(server);
		SocketAddress address = new InetSocketAddress(8080);

		connection.connect(address);
		System.out.println("Server running on http://127.0.0.1:8080/");
	}	
}