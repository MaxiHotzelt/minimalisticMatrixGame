package minimalisticMatrixGame.server.control;

import minimalisticMatrixGame.server.model.Player;
import minimalisticMatrixGame.server.utils.WordGatherer;

public class GameServer extends Thread {

	private Player player1;
	private Player player2;

	private String word;

	private Player winner;
	private boolean running = true;

	public GameServer(Player player1, Player player2) {
		System.out.println("Game Server created for: \n" + player1.getSocket() + "\n" + player2.getSocket());
		this.word = WordGatherer.getInstance().getRandomWord();

		this.player1 = player1;
		this.player2 = player2;

		this.player1.setGameServer(this);
		this.player2.setGameServer(this);

		if (!this.isAlive()) {
			this.start();
		}

	}

	/**
	 * This method is being called from a player, if he guessed the word. If he is
	 * the first to call this method, he is set as winner.
	 * 
	 * @param player - player, who guessed the word
	 */
	public void guessedWord(Player player) {
		if (winner == null) {
			winner = player;
			player.setWon(true);
		}
	}

	@Override
	public void run() {
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

	public String getWord() {
		return this.word;
	}

}
