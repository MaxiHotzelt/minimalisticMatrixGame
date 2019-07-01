package minimalisticMatrixGame.client.model;

public class GameModel {

	private static GameModel game = new GameModel();

	private String soughtword;

	/*
	 * private String soughtWord; private
	 *
	 */

	private boolean won;

	private GameModel() {
		won = false;
	}

	public static GameModel getInstance() {
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
