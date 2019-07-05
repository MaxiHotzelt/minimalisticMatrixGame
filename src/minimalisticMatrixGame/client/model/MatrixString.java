package minimalisticMatrixGame.client.model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class MatrixString {
	private MatrixChar[] chars;
	private int yVelocity;
	private String charPatterForChangingXPos = "iltrfj";

	public MatrixString(String word, int xPos, int yPos) {
		word = changeCharPlace(word);
		chars = new MatrixChar[word.length()];

		int yVelocity = new Random().nextInt(17) + 5;
		createString(word, xPos, yPos, yVelocity);
	}

	private void createString(String word, int xPos, int yPos, int yVelocity) {
		for (int i = 0; i < word.length(); i++) {
			// Short if else
			chars[i] = charPatterForChangingXPos.contains("" + word.charAt(i))
					? new MatrixChar(new Point(xPos + 4, yPos - (30 * i)), word.charAt(i), yVelocity)
					: new MatrixChar(new Point(xPos, yPos - (30 * i)), word.charAt(i), yVelocity);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < chars.length; i++) {
			chars[i].render(g);
		}
	}

	private void changeCharPlace() {
		// Not Implemented
	}

	private String changeCharPlace(String word) {
		StringBuilder sb = new StringBuilder();
		while (!word.isEmpty()) {
			char rdChar = word.charAt(new Random().nextInt(word.length()));
			word = word.replaceFirst(rdChar + "", "");
			sb.append(rdChar);
		}
		return sb.toString();
		// Not Implemented
	}

	public MatrixChar[] getChars() {
		return chars;
	}

}
