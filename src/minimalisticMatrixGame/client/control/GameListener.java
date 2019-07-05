package minimalisticMatrixGame.client.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import minimalisticMatrixGame.client.model.Game;
import minimalisticMatrixGame.client.model.InputField;
import minimalisticMatrixGame.client.utils.GamestateEnum;
import minimalisticMatrixGame.client.view.Container;

public class GameListener implements KeyListener {

	private static GameListener gameListener = new GameListener();
	private Game game;

	private GameListener() {
	}

	/**
	 * 
	 * @return Returns true, if connection to server was possible and new game is
	 *         created, else return false.
	 */
	public boolean createNewGame() {
		this.game = new Game();
		return this.game.getGameClient().connect();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// not needed atm.
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (Container.getInstance().getGamestate() == GamestateEnum.Game) {
			if (e.getKeyCode() == 8) {
				InputField.getInstance().deleteLastChar();
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				checkWords();
			} else if (String.valueOf(e.getKeyChar()).matches(("[a-zA-ZäÄöÖüÜß]"))) {
				InputField.getInstance().addChar(e.getKeyChar());
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// not needed atm.
	}

	private void checkWords() {
		if (InputField.getInstance().getInput().equalsIgnoreCase(this.game.getWord())) {
			this.game.getGameClient().setFinishedGame(true);
		}

	}

	public static GameListener getInstance() {
		return gameListener;
	}

	public Game getGame() {
		return game;
	}
}
