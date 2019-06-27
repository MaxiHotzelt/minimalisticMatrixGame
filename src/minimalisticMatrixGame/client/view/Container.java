package minimalisticMatrixGame.client.view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import minimalisticMatrixGame.client.utils.GamestateEnum;
import minimalisticMatrixGame.client.view.panels.IPanel;
import minimalisticMatrixGame.client.view.panels.impl.Game;
import minimalisticMatrixGame.client.view.panels.impl.Start;

@SuppressWarnings("serial")
public class Container extends JPanel {

	private static Container container = new Container();

	private GamestateEnum gamestate;

	private Container() {
		init();
		config();
		build();
	}

	private void init() {
		gamestate = GamestateEnum.Start;
	}

	private void config() {
		changePanel(Start.getInstance());
	}

	private void build() {
		// not needed
	}

	public static Container getInstance() {
		if (container == null) {
			container = new Container();
		}
		return container;
	}

	public void changePanel(IPanel panel) {
		this.removeAll();

		if (panel instanceof Start) {
			this.setLayout(new GridLayout(3, 1));
		} else if (panel instanceof Game) {
			gamestate = GamestateEnum.Game;
		} else {
			this.setLayout(null);
		}
		for (JComponent c : panel.getComponents()) {
			this.add(c);
		}

		repaint();
		revalidate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (gamestate == GamestateEnum.Game) {
			Game.getInstance().render(g);
		}
	}
}
