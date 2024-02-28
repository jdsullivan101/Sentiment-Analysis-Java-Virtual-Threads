package ie.atu.sw;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.Executors;

/**
 * Generates the map containing the key value pairs from the lexicon.
 * Required in order to execute the sentiment analysis..
 */

public class LexiconMap {

	private Map<String, Double> lexicondata = new ConcurrentHashMap<String, Double>();
	
	/**
	 * Configures the map containing the data from the specified lexicon.<br>
	 * @param pathway, location of the lexicon file.
	 * @return The map of containing the pairs of keys and values.
	 * @throws Exception. Catches exceptions. thrown in the processing of the file example number format exception.
	 *  
	 */

	public Map<String, Double> configureLexicon(String pathway) throws Exception {
		
		//Big O(1) as this is just parsing a single file in a pathway.

		try (var pools = Executors.newVirtualThreadPerTaskExecutor()) {
			Files.lines(Paths.get(pathway)).forEach(text -> pools.execute(() -> processInput(text)));
			//Big O(n) where n is the number of lines in the file to be put into the map. 
			
		} catch (Exception e) {
			System.out.println("Error during processing: " + e.getCause());

		}

		System.out.println("Lexicon loaded to Map. ");
		return lexicondata;
	}
	

	private void processInput(String text) throws NumberFormatException {
		//Big O(n) as this will be based on the length of the text/input size. 
		String[] data = text.split(",");
		String word = String.valueOf(data[0]);
		String vals = data[1];
		double val = Double.parseDouble(vals);
		lexicondata.put(word, val);

	}
}
