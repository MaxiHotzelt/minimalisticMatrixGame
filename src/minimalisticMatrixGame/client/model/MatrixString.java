package minimalisticMatrixGame.client.model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import minimalisticMatrixGame.client.utils.GamestateEnum;
import minimalisticMatrixGame.client.view.Container;

public class MatrixString extends Thread {
	private MatrixChar[] chars;
	private String charPatterForChangingXPos = "iltrfj";
	private int yVelocity = new Random().nextInt(17) + 5;

	public MatrixString(String word, int xPos, int yPos) {
		word = changeCharPlace(word);
		chars = new MatrixChar[word.length()];

		createString(word, xPos, yPos);
	}

	private void createString(String word, int xPos, int yPos) {
		for (int i = 0; i < word.length(); i++) {
			// Short if else
			chars[i] = charPatterForChangingXPos.contains("" + word.charAt(i))
					? new MatrixChar(new Point(xPos + 4, yPos - (30 * i)), word.charAt(i))
					: new MatrixChar(new Point(xPos, yPos - (30 * i)), word.charAt(i));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
