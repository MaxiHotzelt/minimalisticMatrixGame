package minimalisticMatrixGame.client.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import minimalisticMatrixGame.client.control.GameListener;
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
	private final int FRAMERATE = 1000 / 60;

	private Container() {
		init();
		config();
		build();
	}

	private void init() {
		this.gamestate = GamestateEnum.Start;
		this.timer = new Timer(FRAMERATE, new ActionListener() {
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

	/**
	 * This class is there to switch between different Views.
	 * 
	 * @param panel - Panel represents the IPanel, which has to be displayed.
	 */
	public void changePanel(IPanel panel) {
		this.setBackground(null);
		this.removeAll();

		// start
		if (panel instanceof Start) {
			gamestate = GamestateEnum.Start;
			this.setLayout(new GridLayout(3, 1));
		}
		// loading
		else if (panel instanceof Loading) {
			this.gamestate = GamestateEnum.Loading;
		}
		// game
		else if (panel instanceof Game) {
			this.setBackground(Color.black);
			this.requestFocusInWindow();
			Game.getInstance().settings(MainController.getInstance().createMatrixStrings(),
					GameListener.getInstance().getGame().getWord().length());
			gamestate = GamestateEnum.Game;
		}
		// end
		else if (panel instanceof End) {
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

		if (gamestate == GamestateEnum.Game) {
			Game.getInstance().render(g);
		} else if (gamestate == GamestateEnum.Loading) {
			Loading.getInstance().render(g);
		} else if (gamestate == GamestateEnum.End) {
			End.getInstance().render(g);
		}
	}

	/**
	 * This class adds the components from an IPanel to this container, so the
	 * container can display them.
	 * 
	 * @param panel - The Panel, whose components should be displayed.
	 */
	private void addPanelComponents(IPanel panel) {
		for (JComponent c : panel.getComponents()) {
			this.add(c);
		}
	}

	public GamestateEnum getGamestate() {
		return gamestate;
	}

}
