package telran.view;

import java.util.function.Function;

public interface InputOutput {
	String readString(String prompt);
	void write(String str);
	default void writeLine(String str) {
		write(str + "\n");
	}
	default <T> T readObject(String prompt, String errorPrompt,
			Function<String, T> mapper) {
		boolean running = false;
		T res = null;
		do {
			running = false;
			try {
				String str = readString(prompt);
				res = mapper.apply(str);
				
			} catch (Exception e) {
				running = true;
				writeLine(errorPrompt + ": " + e.getMessage());
			}
		}while(running);
		return res;
	}
	
}
