package minimalisticMatrixGame.client.model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class MatrixString {
	private MatrixChar[] chars;

	public MatrixString(String word, int xPos, int yPos) {
		chars = new MatrixChar[word.length()];

		int yVelocity = new Random().nextInt(17) + 5;
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

	private void changeCharPlace() {
		// Not Implemented
	}

	public MatrixChar[] getChars() {
		return chars;
	}

}
