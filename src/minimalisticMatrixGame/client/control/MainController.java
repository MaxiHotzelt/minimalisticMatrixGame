package minimalisticMatrixGame.client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import minimalisticMatrixGame.client.view.Container;
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
				// not needed
			} else {
				JOptionPane.showMessageDialog(Container.getInstance(), "Geben Sie einen Usernamen ein.",
						"Ungültiger Username", JOptionPane.INFORMATION_MESSAGE, null);
			}
		}
	}

}
