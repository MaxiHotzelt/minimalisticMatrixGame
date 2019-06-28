package minimalisticMatrixGame.client.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.panels.impl.Game;

public class GameClient extends Thread {

	private static GameClient gameClient = new GameClient();

	private final int SERVER_PORT = 31337;
	private final String SERVER_IP = "10.176.51.126";
	private Socket socket;
	private PrintWriter writer;
	private Scanner reader;

	public GameClient() {
		// not needed atm
	}

	public void connect() {
		try {
			this.socket = new Socket(SERVER_IP, SERVER_PORT);
			this.writer = new PrintWriter(socket.getOutputStream(), true);
			this.reader = new Scanner(socket.getInputStream());
			Container.getInstance().changePanel(Game.getInstance());
		} catch (IOException e1) {
			e1.printStackTrace();
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
				System.out.println(this.reader.nextLine());
			}
		}
	}

	public static GameClient getInstance() {
		return gameClient;
	}
}
