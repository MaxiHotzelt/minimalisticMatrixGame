package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

import minimalisticMatrixGame.client.control.GameListener;
import minimalisticMatrixGame.client.model.InputField;
import minimalisticMatrixGame.client.model.MatrixChar;
import minimalisticMatrixGame.client.model.MatrixString;
import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.panels.IPanel;

public class Game implements IPanel {

<<<<<<< HEAD
	/**
	 *
	 */
	private static final long serialVersionUID = -7935343836693703356L;

=======
>>>>>>> branch 'master' of https://github.com/MaxiHotzelt/minimalisticMatrixGame.git
	private static Game game = new Game();

	private String word;
	private int yPos = -200;
	private List<MatrixString> matrixstrings;
	private InputField inputfield;

	private Game() {
<<<<<<< HEAD
=======
		init();
	}

	private void init() {
		Container.getInstance().addKeyListener(GameListener.getInstance());
>>>>>>> branch 'master' of https://github.com/MaxiHotzelt/minimalisticMatrixGame.git
	}

	public void start(String word) {
		this.word = word;
		inputfield = InputField.getInstance();
		inputfield.setLength(word.length());
		inputfield.setPosition(new Point(0, 900));
		setupStrings();
	}

	private void tick() {
		for (MatrixString s : matrixstrings) {
			s.tick();
		}
	}

	public void render(Graphics g) {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tick();
		drawMatrixWord(g);
		inputfield.render(g);
	}

	private void drawMatrixWord(Graphics g) {
		for (MatrixString s : matrixstrings) {
			s.render(g);
		}
	}

	private void setupStrings() {
		matrixstrings = new ArrayList<>();
		for (int i = 10; i < 1920; i += MatrixChar.getFont().getSize()) {
			int yPos = new Random().nextInt(200) + 1;
			int vel = new Random().nextInt(15) + 2;
			matrixstrings.add(new MatrixString(word, i, -yPos, vel));
		}
	}

	@Override
	public ArrayList<JComponent> getComponents() {
		return new ArrayList<>();
	}

	public static Game getInstance() {
		return game;
	}

}
