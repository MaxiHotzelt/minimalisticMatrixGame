package minimalisticMatrixGame.client.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class MatrixChar {

	public MatrixChar(Point startingPosition, char character, int yVelocity) {
		this.startingPosition = startingPosition;
		this.character = character;
		this.yVelocity = yVelocity;
		this.yPos = (int) startingPosition.getY();
		this.color = Color.red;
	}

	private final Point startingPosition;
	private final char character;
	private final int yVelocity;
	private static final Font font = new Font("SansSarif", Font.ROMAN_BASELINE, 30);
	private int yPos;
	private final Color color;

	public void tick() {
		if (yPos > 1080) {
			yPos -= 1080;
		} else {
			yPos += yVelocity;
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

}
