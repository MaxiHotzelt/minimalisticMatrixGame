package minimalisticMatrixGame.server.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player extends Thread {
	private Scanner reader;
	private PrintWriter writer;
	private Socket socket;

	private boolean startGame = false;

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

		while (true) {
			if (startGame) {
				System.out.println("in here");
				this.writer.println("Go");
				this.writer.flush();
			}
		}

	}

	public void setStartGame(boolean value) {
		this.startGame = value;
	}

}
