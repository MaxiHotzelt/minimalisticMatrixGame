package minimalisticMatrixGame.client.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import minimalisticMatrixGame.client.utils.StreamReader;

public class GameClient extends Thread {

	private final int SERVER_PORT = 31337;
	private final String SERVER_IP = "localhost";
	private Socket socket;
	private PrintWriter writer;
	private Thread reader;

	private boolean wordGuessed;

	public GameClient() {
		this.wordGuessed = false;
	}

	/**
	 * 
	 * @return Returns true, if connection to server was succesful, else returns
	 *         false.
	 */
	public boolean connect() {

		try {
			this.socket = new Socket(SERVER_IP, SERVER_PORT);
			this.writer = new PrintWriter(socket.getOutputStream(), true);
			this.reader = new Thread(new StreamReader(this.socket));

			if (!this.isAlive()) {
				this.start();
			}

			return true;
		} catch (IOException e1) {
			System.err.println(
					"Couldn't connect to server with \nPort: " + this.SERVER_PORT + " and IP: " + this.SERVER_IP);

			return false;
		}

	}

	@Override
	public void run() {
		super.run();

		reader.start();

		while (!wordGuessed) {
			// do nothing and wait until the player has guessed the word
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Wort wurde erraten :) ");
		this.writer.println("done");
	}

	public void setFinishedGame(boolean finishedGame) {
		System.out.println("Wort wurde gefunden -> Setze wordGuessed auf true!");
		this.wordGuessed = finishedGame;
	}

}
