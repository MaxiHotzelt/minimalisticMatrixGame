package minimalisticMatrixGame.client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.panels.impl.Game;
import minimalisticMatrixGame.client.view.panels.impl.Start;

public class MainController implements ActionListener {

	private static MainController mainController = new MainController();

	private PrintWriter writer;
	private Scanner reader;

	private MainController() {
	}

	public static MainController getInstance() {
		return mainController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Start.getInstance().getStartGameBtn()) {
			if (!Start.getInstance().getUsernameTxtFd().getText().isEmpty()) {
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

			} else {
				JOptionPane.showMessageDialog(Container.getInstance(), "Geben Sie einen Usernamen ein.",
						"Ungültiger Username", JOptionPane.INFORMATION_MESSAGE, null);
			}
		}
	}

	public void write(String message) {
		this.writer.println(message);
		this.writer.flush();
		System.out.println(this.reader.nextLine());
		// break;
	}

	public void close() {
		this.writer.close();
		this.reader.close();
	}

}
