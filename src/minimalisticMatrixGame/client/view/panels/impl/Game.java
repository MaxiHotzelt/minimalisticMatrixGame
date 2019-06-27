package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

import minimalisticMatrixGame.client.model.InputField;
import minimalisticMatrixGame.client.model.MatrixChar;
import minimalisticMatrixGame.client.model.MatrixString;
import minimalisticMatrixGame.client.view.panels.IPanel;

public class Game implements IPanel {

	private static Game game = new Game();

	private String word = "Apfeltasche";
	private String inputWord;
	private int yPos = -200;
	private List<MatrixString> matrixstrings;
	private InputField inputfield;

	private Game() {
		inputfield = new InputField();
		setupStrings();
	}

	private void tick() {
		for (MatrixString s : matrixstrings) {
			s.tick();
		}
		inputfield.tick();
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
//		return null;
		return new ArrayList<>();
	}

	public static Game getInstance() {
		return game;
	}

}
