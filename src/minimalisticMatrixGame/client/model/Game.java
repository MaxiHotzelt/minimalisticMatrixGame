package minimalisticMatrixGame.client.model;

import minimalisticMatrixGame.client.control.ServerConnector;

public class Game {

	private String soughtWord;
	private ServerConnector gameClient;
	private boolean won;

	public Game() {
		this.won = false;
		this.gameClient = new ServerConnector();
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public void setWord(String soughtword) {
		this.soughtWord = soughtword;
	}

	public String getWord() {
		return this.soughtWord;
	}

	public ServerConnector getGameClient() {
		return gameClient;
	}
}
