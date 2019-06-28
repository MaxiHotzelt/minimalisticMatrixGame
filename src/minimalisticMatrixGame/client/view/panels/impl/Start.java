package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import minimalisticMatrixGame.client.control.MainController;
import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.panels.IPanel;

public class Start implements IPanel {

	private static Start start;

	private JLabel usernameLbl;
	private JTextField usernameTxtFd;
	private JButton startGameBtn;
	private JButton testButton;

	private Start() {
		init();
		config();
		build();
	}

	private void init() {
		testButton = new JButton("Start for Test");
		usernameLbl = new JLabel("Username: ");
		usernameTxtFd = new JTextField(20);
		startGameBtn = new JButton("Start Game");

	}

	private void config() {
		this.startGameBtn.addActionListener(MainController.getInstance());
		this.testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Container.getInstance().changePanel(new Game("a"));
			}
		});
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
	public ArrayList<JComponent> getComponentss() {
		ArrayList<JComponent> comp = new ArrayList<JComponent>();
		comp.add(this.usernameLbl);
		comp.add(this.usernameTxtFd);
		comp.add(this.startGameBtn);
		comp.add(this.testButton);

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
