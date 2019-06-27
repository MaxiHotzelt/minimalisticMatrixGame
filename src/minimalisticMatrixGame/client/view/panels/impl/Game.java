package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import minimalisticMatrixGame.client.view.panels.IPanel;

public class Game implements IPanel {

	private static Game game = new Game();

	private Game() {
	}

//	private void drawMatrixWord(Graphics g) {
//		int yVelocity = 1;
//		for (int i = 0; i < word.length(); i++) {
//			g.drawString("" + word.charAt(i), 20, yPos);
////			yPos += 50;
//			yPos += yVelocity;
//		}
//	}

	public void render(Graphics g) {
		g.drawString("Warte auf andere Spieler", 960, 500);
	}

	public static Game getInstance() {
		return game;
	}

	@Override
	public ArrayList<JComponent> getComponents() {
		return new ArrayList<>();
	}

}
