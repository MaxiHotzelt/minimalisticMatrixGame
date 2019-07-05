package minimalisticMatrixGame.server.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class
 * 
 * @author hotzelm & danzk
 * @version 1.0
 */
public class WordGatherer {

	/**
	 * A singleton is used here, because there should be only one instance of this
	 * class.
	 */
	private static WordGatherer wordGatherer;

	/**
	 * All words from the textfile.
	 */
	private ArrayList<String> wordlist;

	private WordGatherer() {
		readWordFile();
	}

	private void readWordFile() {
		wordlist = new ArrayList<>();
		try (FileReader reader = new FileReader("src/minimalisticMatrixGame/server/resource/wortliste.txt")) {
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				addWortToList(line);
			}

			br.close();
			reader.close();
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

	public static WordGatherer getInstance() {
		if (wordGatherer == null) {
			wordGatherer = new WordGatherer();
		}
		return wordGatherer;
	}

}
