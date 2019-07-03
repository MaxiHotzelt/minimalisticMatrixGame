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
		if (e.getSource() == Start.getInstance().getStartGameBtn()) {
			if (!Start.getInstance().getUsernameTxtFd().getText().isEmpty()) {
				GameListener.getInstance().createNewGame();
				Container.getInstance().changePanel(Loading.getInstance());
			} else {
				JOptionPane.showMessageDialog(Container.getInstance(), "Geben Sie einen Usernamen ein.",
						"Ungültiger Username", JOptionPane.INFORMATION_MESSAGE, null);
			}
		} else if (e.getSource() == End.getInstance().getPlayAgainBtn()) {
			Container.getInstance().changePanel(Start.getInstance());
		} else if (e.getSource() == End.getInstance().getQuitBtn()) {
			System.exit(0);
		}
	}

	public ArrayList<MatrixString> createMatrixStringList() {
		String word = GameListener.getInstance().getGame().getWord();
		ArrayList<MatrixString> matrixstrings = new ArrayList<>();
		for (int i = 8; i < Startframe.getInstance().getWidth(); i += MatrixChar.getFont().getSize()) {
			int yPos = new Random().nextInt(200) + 3;
			int vel = new Random().nextInt(17) + 5;
			matrixstrings.add(new MatrixString(word, i, -yPos, vel));
		}
		return matrixstrings;
	}

}
