package minimalisticMatrixGame.server.control;

import java.util.ArrayList;

import minimalisticMatrixGame.server.model.Player;
import minimalisticMatrixGame.server.utils.WordGatherer;

public class GameServer implements Runnable{

	
	private ArrayList<Thread> playerThreads;
	private ArrayList<Player> playerList;
	
	private String word;

	private Player winner;

	public GameServer() {
		
		this.word = WordGatherer.getInstance().getRandomWord();
		playerThreads = new ArrayList<Thread>();
		playerList = new ArrayList<Player>();

	}
	
	public void addPlayer(Player player) {
		player.setGameServer(this);
		
		playerThreads.add(new Thread(player));
		playerList.add(player);
		
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
		boolean gameDone = false;
		for(Thread t : playerThreads) {
			t.start();
		}
		while (true) {
			sleep(1);
			if (isGameDone()) {
				for(Player p : playerList) {
					p.setEndGame(true);
				}
				break;
			}
		}
		for(Thread t : playerThreads) {
			t.interrupt();
		}
	}

	private boolean isGameDone() {
		boolean gameDone = false;
		for(Player p : playerList) {
			if(p.isFinishedGame()) {
				gameDone = true;
			}else {
				return false;
			}
		}
		return gameDone;
	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getWord() {
		return this.word;
	}

}
