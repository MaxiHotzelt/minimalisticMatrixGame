package minimalisticMatrixGame.client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import minimalisticMatrixGame.client.model.MatrixChar;
import minimalisticMatrixGame.client.model.MatrixString;
import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.Startframe;
import minimalisticMatrixGame.client.view.panels.impl.End;
import minimalisticMatrixGame.client.view.panels.impl.Game;
import minimalisticMatrixGame.client.view.panels.impl.Loading;
import minimalisticMatrixGame.client.view.panels.impl.Start;

public class MainController implements ActionListener {

	private static MainController mainController = new MainController();

	private MainController() {
	}

	public static MainController getInstance() {
		return mainController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// start game btn has been pressed
		if (e.getSource() == Start.getInstance().getStartGameBtn()) {

			// checks if the username input field is empty or not
			if (!Start.getInstance().getUsernameTxtFd().getText().isEmpty()) {
				// checks if the server is reachable and swaps to loading screen if the server
				// is available
				if (GameListener.getInstance().createNewGame()) {
					Container.getInstance().changePanel(Loading.getInstance());
				} else {
					JOptionPane.showMessageDialog(Container.getInstance(),
							"Konnte keine Verbindung mit Server herstellen. \nBitte versuchen Sie es erneut.",
							"Server nicht erreichbar", JOptionPane.INFORMATION_MESSAGE, null);
				}
			} else {
				JOptionPane.showMessageDialog(Container.getInstance(), "Geben Sie einen Usernamen ein.",
						"Ungültiger Username", JOptionPane.INFORMATION_MESSAGE, null);
			}
		}
		// play again btn has been pressed
		else if (e.getSource() == End.getInstance().getPlayAgainBtn()) {
			Container.getInstance().changePanel(Start.getInstance());
		}
		// quit button has been pressed
		else if (e.getSource() == End.getInstance().getQuitBtn()) {
			System.exit(0);
		}
	}

	public void startGame() {
		Container.getInstance().changePanel(Game.getInstance());
		Game.getInstance().start();
	}

	public ArrayList<MatrixString> createMatrixStrings() {
		String word = GameListener.getInstance().getGame().getWord();
		ArrayList<MatrixString> matrixStrings = new ArrayList<>();

		for (int i = 8; i < Startframe.getInstance().getWidth(); i += MatrixChar.getFont().getSize()) {
			int yPos = -(new Random().nextInt(200) + 3);
			matrixStrings.add(new MatrixString(word, i, yPos));
		}
		return matrixStrings;
	}

}
