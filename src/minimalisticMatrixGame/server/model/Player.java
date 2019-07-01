package minimalisticMatrixGame.server.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import minimalisticMatrixGame.server.control.GameServer;

public class Player extends Thread {
	private Scanner reader;
	private PrintWriter writer;
	private Socket socket;

	private boolean initGame = false;
	private boolean finishedGame = false;

	private GameServer gameServer;

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

		this.setInitGame(true);
		while (true) {
			if (initGame) {
				this.writer.println("start game");
				this.writer.flush();

				// game doesn't need to be initialized again, so initGame is set to false
				initGame = false;
			} else if (finishedGame) {
				gameServer.setWinner(this);
			}
		}

	}

	public void setInitGame(boolean value) {
		this.initGame = value;
	}

	public void setFinishedGame(boolean finishedGame) {
		this.finishedGame = finishedGame;
	}

	public void setGameServer(GameServer gameserver) {
		this.gameServer = gameserver;
	}

}
