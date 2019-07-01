package minimalisticMatrixGame.client.utils;

import java.util.Locale;

import minimalisticMatrixGame.client.control.GameClient;
import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.panels.impl.Game;

public class MessageHandler {

	private static MessageHandler messageHandler = new MessageHandler();

	private MessageHandler() {

	}

	public void handleMessage(String message) {
		if (message.toLowerCase(Locale.getDefault()).contains("start game")) {
			Container.getInstance().changePanel(Game.getInstance());
			GameClient.getInstance().setGameStarted(true);
		}
	}

	public static MessageHandler getInstance() {
		return messageHandler;
	}
}
