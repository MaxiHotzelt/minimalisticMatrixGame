package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import minimalisticMatrixGame.client.model.GameModel;
import minimalisticMatrixGame.client.view.panels.IPanel;

public class End implements IPanel {

	private static End end = new End();

	private End() {

	}

	public void render(Graphics g) {
		if (GameModel.getInstance().isWon()) {
			g.drawString("You won", 900, 500);
		} else {
			g.drawString("You lost", 900, 500);
		}
	}

	@Override
	public ArrayList<JComponent> getComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	public static End getInstance() {
		return end;
	}

}
