package minimalisticMatrixGame.client.utils;

import java.util.Locale;

import minimalisticMatrixGame.client.control.GameListener;
import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.panels.impl.End;
import minimalisticMatrixGame.client.view.panels.impl.Game;

public class MessageHandler {

	private static MessageHandler messageHandler = new MessageHandler();

	private MessageHandler() {

	}

	public void handleMessage(String message) {
		if (message.toLowerCase(Locale.getDefault()).contains("start game")) {
			Container.getInstance().changePanel(Game.getInstance());
			GameListener.getInstance().getGame().getGameClient().setGameRunning(true);
		} else if (message.toLowerCase(Locale.getDefault()).contains("end game")) {
			if (message.contains("won")) {
				GameListener.getInstance().getGame().setWon(true);
			} else {
				GameListener.getInstance().getGame().setWon(false);
			}

			Container.getInstance().changePanel(End.getInstance());

		} else if (message.toLowerCase(Locale.getDefault()).contains("word")) {
			GameListener.getInstance().getGame().setWord(message.substring(5));
		}
	}

	public static MessageHandler getInstance() {
		return messageHandler;
	}
}
