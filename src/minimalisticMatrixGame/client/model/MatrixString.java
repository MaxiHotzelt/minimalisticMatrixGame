package minimalisticMatrixGame.client.model;

import java.awt.Graphics;
import java.awt.Point;

public class MatrixString {
	private MatrixChar[] chars;
	private int yVelocity;

	public MatrixString(String word, int xPos, int yPos, int yVelocity) {
		this.yVelocity = yVelocity;
		chars = new MatrixChar[word.length()];
		for (int i = 0; i < word.length(); i++) {
			chars[i] = new MatrixChar(new Point(xPos, yPos - (30 * i)), word.charAt(i), yVelocity);
		}
		changeCharPlace();
	}

	public void render(Graphics g) {
		for (int i = 0; i < chars.length; i++) {
			chars[i].render(g);
		}
	}

	public void tick() {
		for (int i = 0; i < chars.length; i++) {
			chars[i].tick();
		}
	}

	private void changeCharPlace() {
		// Not Implemented
	}

	public int getVelocity() {
		return this.yVelocity;
	}
}
