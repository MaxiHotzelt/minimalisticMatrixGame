package minimalisticMatrixGame.client.view.panels.impl;

import java.awt.Graphics;
import java.awt.Point;
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

	private List<MatrixString> matrixstrings;
	private InputField inputfield;
	private boolean ready = false;

	private Game() {
		init();
	}

	private void init() {
		Container.getInstance().addKeyListener(GameListener.getInstance());
	}

	public void settings(List<MatrixString> matrixstrings, int wordlength) {
		this.matrixstrings = matrixstrings;
		
		inputfield = InputField.getInstance();
		inputfield.setLength(wordlength);
		this.ready = true;
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
		drawInputfield(g);
		inputfield.render(g);
	}

	private void drawMatrixWord(Graphics g) {
		for (MatrixString s : matrixstrings) {
			s.render(g);
		}
	}
	
	private void drawInputfield(Graphics g) {
//		g.drawRect(inputfield., y, width, height);
	}

	@Override
	public ArrayList<JComponent> getComponents() {
		return new ArrayList<>();
	}

	public static Game getInstance() {
		return game;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

}
