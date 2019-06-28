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
<<<<<<< HEAD
				String username = Start.getInstance().getUsernameTxtFd().getText();

				try (Socket socket = new Socket("10.176.51.126", 31337)) {
					this.writer = new PrintWriter(socket.getOutputStream(), true);
					this.reader = new Scanner(socket.getInputStream());
					Container.getInstance().changePanel(Game.getInstance());

					while (reader.hasNextLine()) {
//						if(nachricht = go) {
//							starteSpiel();
//							wir senden "finished";
//						}
					}
				} catch (IOException e1) {
					// TODO noch ausgabe, dass server nicht erreichbar
					e1.printStackTrace();
				}

=======
				// not needed amt
>>>>>>> branch 'master' of https://github.com/MaxiHotzelt/minimalisticMatrixGame.git
			} else {
				JOptionPane.showMessageDialog(Container.getInstance(), "Geben Sie einen Usernamen ein.",
						"Ungültiger Username", JOptionPane.INFORMATION_MESSAGE, null);
			}
		}
	}

}
