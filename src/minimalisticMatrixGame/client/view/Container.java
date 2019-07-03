package minimalisticMatrixGame.client.view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import minimalisticMatrixGame.client.control.MainController;
import minimalisticMatrixGame.client.utils.GamestateEnum;
import minimalisticMatrixGame.client.view.panels.IPanel;
import minimalisticMatrixGame.client.view.panels.impl.End;
import minimalisticMatrixGame.client.view.panels.impl.Game;
import minimalisticMatrixGame.client.view.panels.impl.Loading;
import minimalisticMatrixGame.client.view.panels.impl.Start;

@SuppressWarnings("serial")
public class Container extends JPanel {

	private static Container container = new Container();
	private Timer timer;
	private GamestateEnum gamestate;

	private Container() {
		init();
		config();
		build();
	}

	private void init() {
		this.gamestate = GamestateEnum.Start;
		this.timer = new Timer(16, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				repaint();
			}
		});
		timer.start();
	}

	private void config() {
		changePanel(Start.getInstance());
	}

	private void build() {
		// not needed atm
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
			gamestate = GamestateEnum.Start;
			this.setLayout(new GridLayout(4, 1));
		} else if (panel instanceof Loading) {
			this.gamestate = GamestateEnum.Loading;
		} else if (panel instanceof Game) {
			this.requestFocusInWindow();
			gamestate = GamestateEnum.Game;
			Game.getInstance().settings(MainController.getInstance().createMatrixStringList(), 6);
		} else if (panel instanceof End) {
			gamestate = GamestateEnum.End;
			this.setLayout(new GridLayout(2, 1));
		} else {
			this.setLayout(null);
		}

		addPanelComponents(panel);

		repaint();
		revalidate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (gamestate == GamestateEnum.Game && Game.getInstance().isReady()) {
			Game.getInstance().render(g);
		} else if (gamestate == GamestateEnum.Loading) {
			Loading.getInstance().render(g);
		} else if (gamestate == GamestateEnum.End) {
			End.getInstance().render(g);
		}
	}

	private void addPanelComponents(IPanel panel) {
		for (JComponent c : panel.getComponents()) {
			this.add(c);
		}
	}

	public GamestateEnum getGamestate() {
		return gamestate;
	}

}
