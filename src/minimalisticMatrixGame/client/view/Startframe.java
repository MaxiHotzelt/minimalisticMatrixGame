package minimalisticMatrixGame.client.view;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Startframe extends JFrame {

	public static void main(String[] args) {
		new Startframe().setVisible(true);
	}

	private static Startframe startframe = new Startframe();

	private Startframe() {
		init();
		config();
		build();
	}

	private void init() {
		// not needed atm
		this.setLayout(null);
	}

	private void config() {

		int width = 1920;
		int height = 1080;

		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// This block centers the application in the middle of all connected monitors
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gsd = ge.getScreenDevices();
		GraphicsConfiguration gc = gsd[gsd.length / 2].getDefaultConfiguration();
		Rectangle rect = gc.getBounds();

		setLocation((int) rect.getCenterX() - width / 2, (int) rect.getCenterY() - height / 2);
		Container.getInstance().setSize(width, height);
	}

	private void build() {

		this.add(Container.getInstance());
	}

	public static Startframe getInstance() {
		return startframe;
	}
}
