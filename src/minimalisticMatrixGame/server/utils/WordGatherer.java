package minimalisticMatrixGame.server.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordGatherer {

	private ArrayList<String> wordlist;

	public WordGatherer() {
		ReadWordFile();
	}

	private void ReadWordFile() {
		wordlist = new ArrayList<>();
		try {
			FileReader reader = new FileReader("src/minimalisticMatrixGame/server/resource/wortliste.txt");
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				addWortToList(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void addWortToList(String word) {
		wordlist.add(word);
	}

	/*
	 * Returns a random word
	 */
	public String getRandomWord() {
		return wordlist.get(new Random().nextInt(wordlist.size()));
	}

}
