package minimalisticMatrixGame.client.model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;

public class MatrixString {
	private MatrixChar[] chars;
	private int yVelocity;
	private String charPatterForChangingXPos = "iltrfj";

	public MatrixString(String word, int xPos, int yPos, int yVelocity) {
		word = changeCharPlace(word);
		this.yVelocity = yVelocity;
		chars = new MatrixChar[word.length()];
		createString(word, xPos, yPos, yVelocity);
	}

	private void createString(String word, int xPos, int yPos, int yVelocity) {
		for (int i = 0; i < word.length(); i++) {
			//Short if else
			chars [i] = charPatterForChangingXPos.contains(""+word.charAt(i)) ? 
					new MatrixChar(new Point(xPos+4, yPos - (30 * i)), word.charAt(i), yVelocity) : 
					new MatrixChar(new Point(xPos, yPos - (30 * i)), word.charAt(i), yVelocity);
		}
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

	public int getVelocity() {
		return this.yVelocity;
	}
	
}
