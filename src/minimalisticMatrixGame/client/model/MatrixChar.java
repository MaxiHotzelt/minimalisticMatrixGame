package minimalisticMatrixGame.client.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class MatrixChar {

	private final Point startingPosition;
	private final char character;
	private final int resetPointY;
	private static final Font font = new Font("SansSarif", Font.ROMAN_BASELINE, 30);
	private int currentPosY;
	private final Color color;

	public MatrixChar(Point startingPosition, char character, int resetPointY) {
		this.resetPointY = resetPointY;
		this.startingPosition = startingPosition;
		this.character = character;
		this.currentPosY = (int) startingPosition.getY();
		this.color = new Color(13, 254, 38);
	}

	public void move() {
		if (currentPosY > 1080) {
			currentPosY = resetPointY;
		} else {
			currentPosY += 1;
		}
	}

	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		g.drawString("" + this.character, startingPosition.x, currentPosY);
	}

	public static Font getFont() {
		return font;
	}

}
