package minimalisticMatrixGame.client.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import minimalisticMatrixGame.client.utils.MessageHandler;

public class GameClient extends Thread {

	private static GameClient gameClient = new GameClient();

	private final int SERVER_PORT = 31337;
	private final String SERVER_IP = "10.176.51.126";
	private Socket socket;
	private PrintWriter writer;
	private Scanner reader;

	private boolean finishedGame;
	private boolean gameStarted;

	private GameClient() {
		finishedGame = false;
		gameStarted = false;
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

				if (gameStarted) {
					while (!finishedGame) {
						// do nothing
					}

					this.writer.println("Done");
					this.writer.flush();
				}
			}
		}
	}

	public void gameFinished() {
		Long time = System.currentTimeMillis();
		System.out.println(time);
	}

	public static GameClient getInstance() {
		return gameClient;
	}

	public void setFinishedGame(boolean finishedGame) {
		this.finishedGame = finishedGame;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

}
