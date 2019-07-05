package minimalisticMatrixGame.client.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import minimalisticMatrixGame.client.utils.MessageHandler;

public class StreamReader implements Runnable{

	private BufferedReader reader;
	
	public StreamReader(Socket socket) {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String line;
		try {
			while((line = reader.readLine()) != null) {
				MessageHandler.getInstance().handleMessage(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
