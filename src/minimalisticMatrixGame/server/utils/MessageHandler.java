package minimalisticMatrixGame.server.utils;

import java.util.Locale;

import minimalisticMatrixGame.server.model.Player;

public class MessageHandler {

	private static MessageHandler messageHandler = new MessageHandler();

	private MessageHandler() {

	}

	public void handleMessage(String message, Player player) {
		if (message.toLowerCase(Locale.getDefault()).contains("done")) {
			player.setFinishedGame(true);
		}
	}

	public static MessageHandler getInstance() {
		return messageHandler;
	}
}
