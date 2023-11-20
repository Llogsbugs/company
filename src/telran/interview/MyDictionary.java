package telran.interview;

import java.util.*;

public class MyDictionary {
	private TreeSet<String> set;
	
	public MyDictionary(List<String> words) {
		set = new TreeSet<>(words);
	}
	/**
	 * adds word into dictionary
	 * @param word
	 * @return true if new word has been added, false if such word already exists
	 * 
	 */
	public boolean addWord(String word) {
		
		return set.add(word);
	}
	/**
	 * looking for the words with a given prefix
	 * @param prefix
	 * @return array of words with the given prefix
	 */
	public String [] getWordsByPrefix(String prefix) {
		return set.subSet(prefix, getPrefixLimit(prefix))
				.stream().toArray(String[]::new);
	}
	private String getPrefixLimit(String prefix) {
		char lastChar = prefix.charAt(prefix.length() - 1);
		char limitChar = (char) (lastChar + 1);
		return prefix.substring(0, prefix.length() - 1) + limitChar;

	}
}
