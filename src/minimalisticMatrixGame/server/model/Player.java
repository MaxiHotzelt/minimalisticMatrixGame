package minimalisticMatrixGame.server.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player extends Thread {
	private Scanner reader;
	private PrintWriter writer;
	private Socket socket;

	public Player(Socket socket) {
		this.socket = socket;
		try {
			reader = new Scanner(socket.getInputStream());
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Scanner getReader() {
		return reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public Socket getSocket() {
		return socket;
	}

	@Override
	public void run() {
		super.run();

		String incomingMessage;

		while (true) {
			while (this.getReader().hasNextLine()) {
				incomingMessage = this.getReader().nextLine();
				System.out.println(incomingMessage);
//				response = serverProtocol.messageHandler(incomingMessage);
//
//				this.writer.println(response);

			}
		}

	}

}
