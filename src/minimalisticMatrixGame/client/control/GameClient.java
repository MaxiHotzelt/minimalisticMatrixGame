package minimalisticMatrixGame.client.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import minimalisticMatrixGame.client.utils.MessageHandler;

public class GameClient extends Thread {

	private final int SERVER_PORT = 31337;
	private final String SERVER_IP = "localhost";
	private Socket socket;
	private PrintWriter writer;
	private Scanner reader;

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
			this.writer = new PrintWriter(socket.getOutputStream(), true);
			this.reader = new Scanner(socket.getInputStream());
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
		super.run();
		while (true) {
			while (this.reader.hasNextLine()) {
				MessageHandler.getInstance().handleMessage(this.reader.nextLine());
				if (gameRunning) {
					while (!wordGuessed) {
						// do nothing and wait until the player has guessed the word
					}

					this.writer.println("done");
					this.writer.flush();

					// is set to false, because the game has been started
					gameRunning = false;
				}

			}
		}
	}

	public void setFinishedGame(boolean finishedGame) {
		this.wordGuessed = finishedGame;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

}
