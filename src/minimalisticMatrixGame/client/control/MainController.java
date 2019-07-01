package minimalisticMatrixGame.client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import minimalisticMatrixGame.client.model.GameModel;
import minimalisticMatrixGame.client.model.MatrixChar;
import minimalisticMatrixGame.client.model.MatrixString;
import minimalisticMatrixGame.client.view.Container;
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
				GameClient.getInstance().connect();
				Container.getInstance().changePanel(Loading.getInstance());
			} else {
				JOptionPane.showMessageDialog(Container.getInstance(), "Geben Sie einen Usernamen ein.",
						"Ungültiger Username", JOptionPane.INFORMATION_MESSAGE, null);
			}
		}
	}

	public ArrayList<MatrixString> createMatrixStringList() {
		ArrayList<MatrixString> matrixstrings = new ArrayList<>();
		String word = GameModel.getInstance().getWord();
		for (int i = 10; i < 1920; i += MatrixChar.getFont().getSize()) {
			int yPos = new Random().nextInt(200) + 1;
			int vel = new Random().nextInt(15) + 2;
			matrixstrings.add(new MatrixString(word, i, -yPos, vel));
		}
		return matrixstrings;
	}

}
