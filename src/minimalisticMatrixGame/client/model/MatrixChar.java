package minimalisticMatrixGame.client.model;

import java.awt.Graphics;
import java.awt.Point;

public class MatrixChar {

	public MatrixChar(Point startingPosition, char character, int yVelocity) {
		this.startingPosition = startingPosition;
		this.character = character;
		this.yVelocity = yVelocity;
	}

	private final Point startingPosition;
	private final char character;
	private final int yVelocity;
	private Point actualPosition;

	public void tick() {
		actualPosition.y += yVelocity;

	}

	public void render(Graphics g) {
		g.drawString("" + this.character, actualPosition.x, actualPosition.y);
	}

}
