package minimalisticMatrixGame.server.control;

import minimalisticMatrixGame.server.model.Player;

public class GameServer extends Thread {

	private Player player1;
	private Player player2;

	public GameServer(Player player1, Player player2) {

		System.out.println("Game Server created for: \n" + player1.getSocket() + "\n" + player2.getSocket());

		this.player1 = player1;
		this.player2 = player2;

	}

	@Override
	public void run() {
		super.run();

		player1.start();
		player2.start();
		player1.setStartGame(true);
		player2.setStartGame(true);

		while (true) {
			// keep it running
		}

	}

}
