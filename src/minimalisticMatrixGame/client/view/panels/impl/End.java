package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import minimalisticMatrixGame.client.model.Game;
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
	}

	private void build() {
		componentHolder.setLayout(new GridLayout(2, 1));
		componentHolder.add(playAgainBtn);
		componentHolder.add(quitBtn);
	}

	public void render(Graphics g) {
		if (Game.getInstance().isWon()) {
			g.drawString("You won", 900, 500);
		} else {
			g.drawString("You lost", 900, 500);
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

}
