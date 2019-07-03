package minimalisticMatrixGame.server.control;

import java.net.ServerSocket;

public class Server {

	private static Server server = new Server();

	private int port;

	private Server() {
		init();
	}

	private void init() {
		port = 31337;
	}

	public void start() {
		System.out.println("Server is running and waiting for clients...");
		try (ServerSocket server = new ServerSocket(port)) {
			while (true) {
				ClientHandler.getInstance().addClient(server.accept());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Server getInstance() {
		return server;
	}

}
