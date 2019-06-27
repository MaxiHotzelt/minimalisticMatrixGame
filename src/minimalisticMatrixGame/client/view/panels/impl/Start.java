package minimalisticMatrixGame.client.view.panels.impl;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import minimalisticMatrixGame.client.control.MainController;
import minimalisticMatrixGame.client.view.panels.IPanel;

public class Start implements IPanel {

	private static Start start;

	private JLabel usernameLbl;
	private JTextField usernameTxtFd;
	private JButton startGameBtn;

	private Start() {
		init();
		config();
		build();
	}

	private void init() {
		usernameLbl = new JLabel("Username: ");
		usernameTxtFd = new JTextField(20);
		startGameBtn = new JButton("Start Game");

	}

	private void config() {
		this.startGameBtn.addActionListener(MainController.getInstance());
	}

	private void build() {
		// TODO Auto-generated method stub

	}

	public static Start getInstance() {
		if (start == null) {
			start = new Start();
		}
		return start;
	}

	@Override
	public ArrayList<JComponent> getComponents() {
		ArrayList<JComponent> comp = new ArrayList<JComponent>();
		comp.add(this.usernameLbl);
		comp.add(this.usernameTxtFd);
		comp.add(this.startGameBtn);

		return comp;
	}

	public JLabel getUsernameLbl() {
		return usernameLbl;
	}

	public JTextField getUsernameTxtFd() {
		return usernameTxtFd;
	}

	public JButton getStartGameBtn() {
		return startGameBtn;
	}

}
