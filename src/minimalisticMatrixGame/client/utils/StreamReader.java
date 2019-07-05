package minimalisticMatrixGame.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This class is there to asynchronously listen for messages from a server.
 * 
 * @author hotzelm
 *
 */
public class StreamReader implements Runnable {

	private BufferedReader reader;

	public StreamReader(Socket socket) {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String message;

		try {
			while ((message = reader.readLine()) != null) {
				MessageHandler.getInstance().handleMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
