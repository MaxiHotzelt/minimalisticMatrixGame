package minimalisticMatrixGame.server.control;

import minimalisticMatrixGame.client.model.Game;
import minimalisticMatrixGame.server.model.Player;
import minimalisticMatrixGame.server.utils.WordGatherer;

public class GameServer extends Thread {

	private Player player1;
	private Player player2;

	private WordGatherer wordgatherer;

	private Player winner;
	private boolean running = true;

	public GameServer(Player player1, Player player2) {

		wordgatherer = new WordGatherer();

		System.out.println("Game Server created for: \n" + player1.getSocket() + "\n" + player2.getSocket());

		this.player1 = player1;
		this.player2 = player2;

		this.player1.setGameServer(this);
		this.player2.setGameServer(this);

	}

	// is being called, if a player finished the game
	public void finishedGame(Player player) {
		if (winner == null) {
			winner = player;
			player.setWon(true);
		}
	}

	@Override
	public void run() {
		Game.getInstance().setWord(wordgatherer.getRandomWord());
		super.run();
		player1.start();
		player2.start();

		while (running) {
			if (player1.isFinishedGame() && player2.isFinishedGame()) {
				player1.setEndGame(true);
				player2.setEndGame(true);

				running = false;
			}
		}

	}

}
