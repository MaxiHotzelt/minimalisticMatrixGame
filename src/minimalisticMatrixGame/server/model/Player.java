package minimalisticMatrixGame.server.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import minimalisticMatrixGame.server.control.GameServer;
import minimalisticMatrixGame.server.utils.MessageHandler;

public class Player implements Runnable{
	private Scanner reader;
	private PrintWriter writer;
	private Socket socket;

	private boolean sendEnd = false;
	private boolean finishedGame = false;
	private boolean endGame = false;
	private boolean gameRunning = true;

	private boolean won = false;

	private GameServer gameServer;

	public Player(Socket socket) {
		this.socket = socket;
		try {
			reader = new Scanner(socket.getInputStream());
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		this.writer.println("word#" + gameServer.getWord());
		this.writer.println("start game");
		MessageHandler.getInstance().handleMessage(this.reader.nextLine(), this);
		while (gameRunning) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
				if (finishedGame) {
				// this only should be called once - right after the word is guessed
				if (!sendEnd) {
					gameServer.guessedWord(this);
					sendEnd = true;
				}
			}
			if (endGame) {
				if (won) {
					this.writer.println("end game won");
				} else {
					this.writer.println("end game lost");
				}

				gameRunning = false;
			}
		}

	}


	public void setFinishedGame(boolean finishedGame) {
		this.finishedGame = finishedGame;
	}

	public void setGameServer(GameServer gameserver) {
		this.gameServer = gameserver;
	}

	public boolean isFinishedGame() {
		return finishedGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	public void setWon(boolean won) {
		this.won = won;
	}


}
