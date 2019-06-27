package minimalisticMatrixGame.server.control;

import java.net.Socket;
import java.util.ArrayList;

import minimalisticMatrixGame.server.model.Player;

public class ClientHandler extends Thread {

	private static ClientHandler clientHandler = new ClientHandler();

	private static ArrayList<Player> waitingPool = new ArrayList<>();

	private ClientHandler() {
	}

	public void addClient(Socket client) {

		waitingPool.add(new Player(client));

		if (!this.isAlive()) {
			this.start();
		}
	}

	@Override
	public void run() {
		while (true) {
			if (waitingPool.size() == 2) {
				new GameServer(waitingPool.get(0), waitingPool.get(1));
				waitingPool.remove(1);
				waitingPool.remove(0);
			}
		}
	}

	public static ClientHandler getInstance() {
		return clientHandler;
	}

}
