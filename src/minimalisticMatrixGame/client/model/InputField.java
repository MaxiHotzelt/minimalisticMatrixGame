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
	private Color charColor;
	private Color fieldColor;
	private boolean changeChars;
	private boolean wrongAnswer;
	private boolean rumbleLeft;
	private Thread timer;
	private int rumble;
	

	private InputField() {
		changeChars = true;
		charColor = Color.red;
		fieldColor = Color.black;
		chars = new char[0];
		point = new Point(0, 0);
		
	}

	public void render(Graphics g) {
		renderRect(g);
		if(wrongAnswer) {
			if(rumbleLeft) {
				renderInputField(g, rumble-=3);
			}else {
				renderInputField(g, rumble+=3);
			}
		}else {
			renderInputField(g);
		}
	}

	private void renderRect(Graphics g) {
		g.setColor(fieldColor);
		g.fillRect(point.x-18, point.y+10, (int)rectDimension.getHeight(), (int)rectDimension.getWidth());
	}

	private void renderInputField(Graphics g,int rumbleNumber) {
		g.setColor(charColor);
		for (int i = 0; i < chars.length; i++) {
				g.drawString("" + chars[i], (int) point.getX() + (spaceBetweenChars * i)+ rumbleNumber, (int) point.getY()+45);
		}
		if (rumbleNumber <= -3) {
			this.rumbleLeft = false;
		}else if(rumbleNumber >=3) {
			this.rumbleLeft = true;
		}
	}
	
	private void renderInputField(Graphics g) {
		g.setColor(charColor);
		for (int i = 0; i < chars.length; i++) {
			g.drawString("" + chars[i], (int) point.getX() + (spaceBetweenChars * i), (int) point.getY()+45);
		}
	}

	public void tick() {
		// not needed
	}

	public String getInput() {
		return String.valueOf(chars);
	}

	public void settupNewGame(int length) {
		charColor = Color.red;
		changeChars = true;
		chars = new char[length];
		clearField();
		setPosition();
	}

	private void setPosition() {
		int maxSpace = spaceBetweenChars*chars.length;
		int xPos =  (Startframe.getInstance().getWidth()/2) - (maxSpace/2)^2 ;
		this.point = new Point(xPos, 900);
		this.rectDimension = new Dimension(50,maxSpace+25);
	}

	public static InputField getInstance() {
		if (instance == null) {
			instance = new InputField();
		}
		return instance;
	}

	public void addChar(char keyChar) {
		if(changeChars) {
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == '_') {
					chars[i] = keyChar;
					break;
				} else if (i == chars.length - 1) {
					chars[i] = keyChar;
				}
			}
		}

	}

	public void deleteLastChar() {
		if(changeChars) {
			for (int i = chars.length - 1; i >= 0; i--) {
				if (chars[i] != '_') {
					chars[i] = '_';
					break;
				}
			}
		}
	}
	
	public void clearField() {
		for(int i = 0; i < chars.length;i++) {
			chars[i] = '_';
		}
	}
	
	public void rightInput() {
		this.charColor = new Color(207,181,59);
		this.changeChars = false;
	}

	public void wrongInput() {
		clearField();
		timer = new Thread(() -> {
			System.out.println("timer start");
			wrongAnswer = true;
			rumbleLeft = true;
			try {
				timer.sleep(750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wrongAnswer = false;
			System.out.println("timer done");
		});
		timer.start();
		
	}
	
	
}
