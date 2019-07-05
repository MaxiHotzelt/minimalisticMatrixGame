package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import minimalisticMatrixGame.client.control.GameListener;
import minimalisticMatrixGame.client.model.InputField;
import minimalisticMatrixGame.client.model.MatrixString;
import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.panels.IPanel;

public class Game implements IPanel {

	private static Game game = new Game();

	private List<MatrixString> matrixStrings;
	private InputField inputField;

	private Game() {
		init();
	}

	private void init() {
		Container.getInstance().addKeyListener(GameListener.getInstance());
	}

	public void settings(List<MatrixString> matrixstrings, int wordlength) {
		this.matrixStrings = matrixstrings;

		inputField = InputField.getInstance();
		inputField.settupNewGame(wordlength);
	}

	public void start() {
		for (MatrixString s : matrixStrings) {
			s.start();
		}
	}

	public void render(Graphics g) {
		for (MatrixString s : matrixStrings) {
			s.render(g);
		}
		inputField.render(g);
	}

	@Override
	public ArrayList<JComponent> getComponents() {
		return new ArrayList<>();
	}

	public static Game getInstance() {
		return game;
	}

	public List<MatrixString> getMatrixStrings() {
		return matrixStrings;
	}

}
