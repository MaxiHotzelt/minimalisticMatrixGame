package minimalisticMatrixGame.client.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import minimalisticMatrixGame.client.model.GameModel;
import minimalisticMatrixGame.client.model.InputField;
import minimalisticMatrixGame.client.utils.GamestateEnum;
import minimalisticMatrixGame.client.view.Container;

public class GameListener implements KeyListener {

	private static GameListener gameListener = new GameListener();

	private GameListener() {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (Container.getInstance().getGamestate() == GamestateEnum.Game) {
			if (e.getKeyCode() == 8) {
				InputField.getInstance().deleteLastChar();
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				checkWords();
			} else if (String.valueOf(e.getKeyChar()).matches(("[a-zA-Z]"))) {
				InputField.getInstance().addChar(e.getKeyChar());
			}
			// nach jedem Tastendruck muss der Controller das inputfield nehmen und dieses
			// mit dem lösungswort abprüfen
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void checkWords() {
		if (InputField.getInstance().getInput().equalsIgnoreCase(GameModel.getInstance().getWord())) {
			System.out.println("GEWONNEN!!!!!");
			GameClient.getInstance().setFinishedGame(true);
		}

	}

	public static GameListener getInstance() {
		return gameListener;
	}
}
