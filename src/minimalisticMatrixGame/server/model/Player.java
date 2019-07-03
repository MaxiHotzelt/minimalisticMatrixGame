package minimalisticMatrixGame.server.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import minimalisticMatrixGame.server.control.GameServer;
import minimalisticMatrixGame.server.utils.MessageHandler;

public class Player extends Thread {
	private Scanner reader;
	private PrintWriter writer;
	private Socket socket;

	private boolean initGame = false;
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

	public Scanner getReader() {
		return reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public Socket getSocket() {
		return socket;
	}

	@Override
	public void run() {
		super.run();

		this.setInitGame(true);
		while (gameRunning) {
			if (initGame) {
				this.writer.println("word#" + gameServer.getWord());
				this.writer.println("start game");
				this.writer.flush();

				this.reader.hasNext();
				MessageHandler.getInstance().handleMessage(this.reader.nextLine(), this);

				// game doesn't need to be initialized again, so initGame is set to false
				initGame = false;
			} else if (finishedGame) {
				// this only should be called once - right after the word is guessed
				if (!sendEnd) {
					gameServer.finishedGame(this);
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

	public void setInitGame(boolean value) {
		this.initGame = value;
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
