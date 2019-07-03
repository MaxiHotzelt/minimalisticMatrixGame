package minimalisticMatrixGame.client.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


import minimalisticMatrixGame.client.view.Startframe;

public class InputField {

	private static InputField instance;

	private char[] chars;
	private Point point;
	private final int spaceBetweenChars = 30;
	private Dimension rectDimension;

	private InputField() {
		chars = new char[0];
		point = new Point(0, 0);
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(point.x-30, point.y+10, (int)rectDimension.getHeight(), (int)rectDimension.getWidth());
		g.setColor(Color.red);
		for (int i = 0; i < chars.length; i++) {
			g.drawString("" + chars[i], (int) point.getX() + (spaceBetweenChars * i), (int) point.getY()+50);
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
		setPosition();
	}

	private void setPosition() {
		int maxSpace = spaceBetweenChars*chars.length;
		int xPos =  (Startframe.getInstance().getWidth()/2) - (maxSpace/2)^2 ;
		this.point = new Point(xPos, 900);
		this.rectDimension = new Dimension(50,maxSpace+30);
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
