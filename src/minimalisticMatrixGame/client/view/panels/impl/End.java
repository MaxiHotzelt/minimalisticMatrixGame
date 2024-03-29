package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import minimalisticMatrixGame.client.control.GameListener;
import minimalisticMatrixGame.client.control.MainController;
import minimalisticMatrixGame.client.view.panels.IPanel;

public class End implements IPanel {

	private static End end = new End();

	private JPanel componentHolder;
	private JButton playAgainBtn;
	private JButton quitBtn;

	private End() {
		init();
		config();
		build();
	}

	private void init() {
		componentHolder = new JPanel();
		playAgainBtn = new JButton("Play again");
		quitBtn = new JButton("Quit");
	}

	private void config() {
		this.componentHolder.setSize(500, 200);
		this.playAgainBtn.addActionListener(MainController.getInstance());
		this.quitBtn.addActionListener(MainController.getInstance());
	}

	private void build() {
		componentHolder.setLayout(new GridLayout(2, 1));
		componentHolder.add(playAgainBtn);
		componentHolder.add(quitBtn);
	}

	public void render(Graphics g) {
		if (GameListener.getInstance().getGame().isWon()) {
			g.drawString("You won", 900, 800);
		} else {
			g.drawString("You lost", 900, 800);
		}
	}

	@Override
	public ArrayList<JComponent> getComponents() {
		ArrayList<JComponent> comp = new ArrayList<>();
		comp.add(componentHolder);
		return comp;
	}

	public static End getInstance() {
		return end;
	}

	public JButton getPlayAgainBtn() {
		return playAgainBtn;
	}

	public JButton getQuitBtn() {
		return quitBtn;
	}

}
