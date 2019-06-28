package minimalisticMatrixGame.client.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import minimalisticMatrixGame.client.model.InputField;
import minimalisticMatrixGame.client.view.Container;

public class GameListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 8) {
			InputField.getInstance().deleteLastChar();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			checkWords();
		} else {
			InputField.getInstance().addChar(e.getKeyChar());
		}
		// nach jedem Tastendruck muss der Controller das inputfield nehmen und dieses
		// mit dem lösungswort abprüfen

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void checkWords() {
//		String inputWord = String.valueOf(InputField.getInstance().getInput());
		if (InputField.getInstance().getInput().equalsIgnoreCase(Container.getInstance().getWord())) {
			System.out.println("GEWONNEN!!!!!");
		}

	}
}
