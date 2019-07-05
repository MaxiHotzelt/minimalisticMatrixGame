package minimalisticMatrixGame.client.model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import minimalisticMatrixGame.client.utils.GamestateEnum;
import minimalisticMatrixGame.client.view.Container;

public class MatrixString extends Thread {
	private MatrixChar[] chars;
	private String charPatterForChangingXPos = "iltrfj";
	private int yVelocity = new Random().nextInt(5) + 1;

	private final int X_OFFSET = 4;
	/**
	 * This final variable represents the vertical space between each char
	 */
	private final int Y_OFFSET = 30;

	public MatrixString(String word, int xPos, int yPos) {
		word = changeCharPlace(word);
		chars = new MatrixChar[word.length()];

		createMatrixString(word, xPos, yPos);
	}

	private void createMatrixString(String word, int xPos, int yPos) {
		for (int i = 0; i < word.length(); i++) {
			// Short if else
			chars[i] = charPatterForChangingXPos.contains("" + word.charAt(i))
					? new MatrixChar(new Point(xPos + X_OFFSET, yPos - (Y_OFFSET * i)), word.charAt(i), yPos)
					: new MatrixChar(new Point(xPos, yPos - (Y_OFFSET * i)), word.charAt(i), yPos);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < chars.length; i++) {
			chars[i].render(g);
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
	}

	@Override
	public void run() {
		super.run();

		while (Container.getInstance().getGamestate() == GamestateEnum.Game) {

			for (MatrixChar c : chars) {
				c.move();
			}

			try {
				Thread.sleep(this.yVelocity);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
