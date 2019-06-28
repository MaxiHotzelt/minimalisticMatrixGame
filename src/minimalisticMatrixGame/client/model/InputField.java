package minimalisticMatrixGame.client.model;

import java.awt.Graphics;
import java.awt.Point;

public class InputField {

	private static InputField instance;

	private char[] chars;
	private Point point;

	private InputField() {
		chars = new char[0];
		point = new Point(0, 0);
	}

	public void render(Graphics g) {
		for (int i = 0; i < chars.length; i++) {
			g.drawString("" + chars[i], (int) point.getX() + (30 * i), (int) point.getY());
		}
	}

	public void tick() {
		// not needed
	}

	public String getInput() {
		return String.valueOf(chars);
//		return Arrays.toString(chars);
	}

	public void setLength(int length) {
		chars = new char[length];
		for (int i = 0; i < length; i++) {
			chars[i] = '_';
		}
	}

	public void setPosition(Point point) {
		this.point = point;
	}

	public static InputField getInstance() {
		if (instance == null) {
			instance = new InputField();
		}
		return instance;
	}

	public void addChar(char keyChar) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '_') {
				chars[i] = keyChar;
				break;
			} else if (i == chars.length - 1) {
				chars[i] = keyChar;
			}
		}

	}

	public void deleteLastChar() {
		for (int i = chars.length - 1; i >= 0; i--) {
			if (chars[i] != '_') {
				chars[i] = '_';
				break;
			}
		}
	}
}
