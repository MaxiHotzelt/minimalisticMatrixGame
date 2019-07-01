package minimalisticMatrixGame.client.model;

public class Game {

	private static Game game = new Game();

	private boolean won;

	private Game() {
		won = false;
	}

	public static Game getInstance() {
		return game;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}
}
