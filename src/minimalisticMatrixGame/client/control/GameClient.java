package minimalisticMatrixGame.client.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import minimalisticMatrixGame.client.utils.MessageHandler;

public class GameClient extends Thread {

	private Thread in;
	private final int SERVER_PORT = 31337;
	private final String SERVER_IP = "localhost";
	private Socket socket;
	private PrintWriter writer;

	private boolean wordGuessed;
	/**
	 * This variable tells, if the game inside the application is running (and not
	 * the application itself).
	 */
	private boolean gameRunning;

	public GameClient() {
		wordGuessed = false;
		gameRunning = false;
	}

	public void connect() {
		try {
			this.socket = new Socket(SERVER_IP, SERVER_PORT);
			this.writer = new PrintWriter(this.socket.getOutputStream(), true);
			in = new Thread(new StreamReader(this.socket));
		} catch (IOException e1) {
			System.err.println(
					"Couldn't connect to server with \nPort: " + this.SERVER_PORT + " and IP: " + this.SERVER_IP);
		}

		if (!this.isAlive()) {
			this.start();
		}

	}

	@Override
	public void run() {
		in.start();
		super.run();
				do {
					try {
						this.sleep(1);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}while(!wordGuessed);
						System.out.println("wort wurde erraten :) ");
						// do nothing and wait until the player has guessed the word
						this.writer.println("done");
						gameRunning = false;
	}

	public void setFinishedGame(boolean finishedGame) {
		System.out.println("Wort wurde gefunden -> Setze wordGuessed auf true!");
		this.wordGuessed = finishedGame;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

}
