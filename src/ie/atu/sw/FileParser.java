package ie.atu.sw;

import java.io.File;
import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import java.util.concurrent.ConcurrentLinkedDeque;

import java.util.concurrent.Executors;

/**
 * <p>Utility class for the parsing of the directory of the tweets selected the user.
 * Puts data from a directory into a concurrent linked deque using virtual
 * threads. These will be passed to the sentiment analysis via the menu class. 
 * </p>
 */
public class FileParser {

	private static int tweetline = 0;
	private Collection<String> words = new ConcurrentLinkedDeque<>();
	
	/**
	 * <p>Generates the concurrent linked deque that contains the tweets to be analysed.
	 * </p>
	 * @param directory, Location of the tweets to be parsed into the collection.
	 * @return Collection of the tweets in a concurrent linked deque
	 * @throws IOException
	 */

	public Collection<String> parse(String directory) throws Exception {

		File tweetFiles = new File(directory);
		File[] tweets = tweetFiles.listFiles();

		for (File file : tweets) {
			/*
			 * Big O for this method is O(n^2) where n is based on the number of elements. This is because of the iteration of the 
			 * number of files in the directory, and the number of lines in the files.
			 */
			try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
				Files.lines(Paths.get(file.toURI())).forEach(text -> pool.execute(() -> processFile(text, ++tweetline)));

			} catch (Exception e) {
			}

		}
		System.out.println("Tweets successfully parsed. The number of tweets to be analysed is: " + words.size());
		return words;
	}

	private void processFile(String text, int tweetline) {
		String lcWords = text.toLowerCase();
		/*
		 * Big O for adding to the linkedDeque is O(1).This is beacuse deques are able to 
		 * add elements to either the front or back of the deque, and adjusts the reference point. 
		 */
		Arrays.stream(lcWords.split("\\n+")).forEach(w -> words.add(w));

	}
}
