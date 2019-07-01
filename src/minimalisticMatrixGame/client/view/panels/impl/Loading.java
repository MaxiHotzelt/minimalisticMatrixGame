package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import minimalisticMatrixGame.client.view.panels.IPanel;

public class Loading implements IPanel {

	private static Loading loading = new Loading();

	private Loading() {

	}

	public void render(Graphics g) {
		g.drawString("Loading", 1000, 500);
	}

	@Override
	public ArrayList<JComponent> getComponents() {
		return new ArrayList<>();
	}

	public static Loading getInstance() {
		return loading;
	}
}
