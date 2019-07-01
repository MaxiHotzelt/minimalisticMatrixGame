package minimalisticMatrixGame.client.model;

public class Game {

	private static Game game = new Game();

	private String soughtword;

	/*
	 * private String soughtWord; private
	 *
	 */

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

	public void setWord(String soughtword) {
		this.soughtword = soughtword;
	}

	public String getWord() {
		return this.soughtword;
	}
}
