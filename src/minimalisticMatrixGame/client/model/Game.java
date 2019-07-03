package minimalisticMatrixGame.client.model;

import minimalisticMatrixGame.client.control.GameClient;

public class Game {

	private String soughtword;
	private GameClient gameClient;
	private boolean won;

	public Game() {
		this.won = false;
		this.gameClient = new GameClient();
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

	public GameClient getGameClient() {
		return gameClient;
	}
}
