package telran.view;

import java.time.LocalDate;
import java.util.function.Function;

public interface InputOutput {
	String readString(String prompt);
	void write(String str);
	default void writeLine(Object obj) {
		write(obj.toString() + "\n");
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
	default int readInt(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Integer::parseInt);
	}
	default int readInt(String prompt, String errorPrompt, int min, int max) {
		return readObject(String.format("%s [%d-%d]" , prompt, min, max), errorPrompt, str -> {
			int num = Integer.parseInt(str);
			if (num < min || num > max) {
				throw new RuntimeException
				(String.format("must be in the range [%d-%d]", min, max));
			}
			return num;
		});
	}
	default long readLong(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Long::parseLong);
	}
	default long readLong(String prompt, String errorPrompt, long min, long max) {
		return readObject(String.format("%s [%d-%d]" , prompt, min, max), errorPrompt, str -> {
			long num = Long.parseLong(str);
			if (num < min || num > max) {
				throw new RuntimeException
				(String.format("must be in the range [%d-%d]", min, max));
			}
			return num;
		});
	}
	default LocalDate readDate(String prompt, String errorPrompt) {
		//example: 2023-11-20
		return readObject(prompt, errorPrompt, LocalDate::parse);
	}
	
	
	
	
	
	
	
	
	
	
	
		
	
}
