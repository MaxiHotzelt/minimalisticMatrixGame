package minimalisticMatrixGame.server.control;

import java.net.Socket;
import java.util.ArrayList;

import minimalisticMatrixGame.server.model.Player;

public class ClientHandler implements Runnable {

	private static ClientHandler clientHandler = new ClientHandler();

	private static ArrayList<Player> waitingPool = new ArrayList<>();

	private ClientHandler() {
	}

	public void addClient(Socket client) {
		System.out.println("New Client: \n" + client);
		waitingPool.add(new Player(client));
	}

	@Override
	public void run() {
		final int MAX_PLAYERS_PER_GAME = 2;
		while (true) {
			if (waitingPool.size() >= MAX_PLAYERS_PER_GAME) {
				GameServer game = new GameServer();
				for(int i = 0 ; i < MAX_PLAYERS_PER_GAME; i++) {
					game.addPlayer(waitingPool.get(0));
					waitingPool.remove(0);
				}
				Thread gameThread = new Thread(game);
				gameThread.start();
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static ClientHandler getInstance() {
		return clientHandler;
	}

}
