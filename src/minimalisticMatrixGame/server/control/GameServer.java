package minimalisticMatrixGame.server.control;

import minimalisticMatrixGame.server.model.Player;

public class GameServer extends Thread {

	private Player player1;
	private Player player2;

	public GameServer(Player player1, Player player2) {

		System.out.println("Game Server created for: \n" + player1 + "\n" + player2);

		this.player1 = player1;
		this.player2 = player2;
	}

	@Override
	public void run() {
		super.run();

		GameThread a = new GameThread(player1);
		GameThread b = new GameThread(player2);

		while (true) {

		}

	}

}
