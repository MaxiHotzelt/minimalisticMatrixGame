package minimalisticMatrixGame.client.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class MatrixChar extends Thread {

	private final Point startingPosition;
	private final char character;
	private static final Font font = new Font("SansSarif", Font.ROMAN_BASELINE, 30);
	private int yPos;
	private final Color color;
	private int yVelocity;
	private boolean gameRunning;

	public MatrixChar(Point startingPosition, char character, int yVelocity) {
		this.startingPosition = startingPosition;
		this.character = character;
		this.yPos = (int) startingPosition.getY();
		this.color = Color.red;
	}

	private void move() {
		if (yPos > 1080) {
			yPos -= 1080;
		} else {
			yPos += 1;
		}
	}

	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		g.drawString("" + this.character, startingPosition.x, yPos);
	}

	public static Font getFont() {
		return font;
	}

	@Override
	public void run() {
		super.run();

		while (gameRunning) {
			this.move();

			try {
				Thread.sleep(this.yVelocity);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
