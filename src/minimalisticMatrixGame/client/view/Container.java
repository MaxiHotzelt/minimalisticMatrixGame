package minimalisticMatrixGame.client.view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import minimalisticMatrixGame.client.control.GameListener;
import minimalisticMatrixGame.client.utils.GamestateEnum;
import minimalisticMatrixGame.client.view.panels.IPanel;
import minimalisticMatrixGame.client.view.panels.impl.Game;
import minimalisticMatrixGame.client.view.panels.impl.Start;

@SuppressWarnings("serial")
public class Container extends JPanel {

	private static Container container = new Container();
	private Game game;
	private String soughtWord;

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
			// Zu testzwecken setzen wir einen weiteren Button hinen -> testButton -> 4
			// müsste eigentlich 3 sein
			this.setLayout(new GridLayout(4, 1));
			addPanelComponents(panel);
		} else if (panel instanceof Game) {
			soughtWord = "Apfeltasche";
//			this.requestFocusInWindow();
			this.addKeyListener(new GameListener());
			gamestate = GamestateEnum.Game;
			Game.getInstance().start(soughtWord);
		} else {
			this.setLayout(null);
		}

		repaint();
		revalidate();
	}

	private void addPanelComponents(IPanel panel) {
		for (JComponent c : panel.getComponents()) {
			this.add(c);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (gamestate == GamestateEnum.Game) {
			Game.getInstance().render(g);
			repaint();
		}
	}

	public String getWord() {
		return this.soughtWord;
	}
}
