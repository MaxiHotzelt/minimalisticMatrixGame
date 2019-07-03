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
		System.out.println("New Client: \n" + client);
		waitingPool.add(new Player(client));
		if (!this.isAlive()) {
			this.start();
		}
	}

	@Override
	public void run() {
		final int MAX_PLAYERS_PER_GAME = 2;
		while (true) {
			if (waitingPool.size() >= MAX_PLAYERS_PER_GAME) {
				new GameServer(waitingPool.get(0), waitingPool.get(1));
				waitingPool.remove(1);
				waitingPool.remove(0);
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static ClientHandler getInstance() {
		return clientHandler;
	}

}
