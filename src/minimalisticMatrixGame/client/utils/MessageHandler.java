package minimalisticMatrixGame.client.utils;

import java.util.Locale;

import minimalisticMatrixGame.client.control.GameClient;
import minimalisticMatrixGame.client.view.Container;
import minimalisticMatrixGame.client.view.panels.impl.End;
import minimalisticMatrixGame.client.view.panels.impl.Game;
import minimalisticMatrixGame.server.utils.WordGatherer;

public class MessageHandler {

	private static MessageHandler messageHandler = new MessageHandler();

	private MessageHandler() {

	}

	public void handleMessage(String message) {
		System.out.println(message);
		if (message.toLowerCase(Locale.getDefault()).contains("start game")) {
//			minimalisticMatrixGame.client.model.Game.getInstance().setWord(WordGatherer.getInstance().getRandomWord());
			Container.getInstance().changePanel(Game.getInstance());
			GameClient.getInstance().setGameRunning(true);
		} else if (message.toLowerCase(Locale.getDefault()).contains("end game")) {
			if (message.contains("won")) {
				minimalisticMatrixGame.client.model.Game.getInstance().setWon(true);
			} else {
				minimalisticMatrixGame.client.model.Game.getInstance().setWon(false);
			}

			Container.getInstance().changePanel(End.getInstance());
		}else if(message.toLowerCase(Locale.getDefault()).contains("word")) {
			minimalisticMatrixGame.client.model.Game.getInstance().setWord(message.substring(5));
		}
	}

	public static MessageHandler getInstance() {
		return messageHandler;
	}
}
