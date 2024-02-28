package ie.atu.sw;

import java.util.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Calculates the sentiment of the tweets and writes the results to a text file.
 * Extends the OutWriter class so that the output can be written to a file.
 * Implements the Summation abstract class, to perform the calculation of the sentiment. 
 */
public class SentimentAnalysis extends OutWriter implements Summation {

	private double score = 0;
	private double sft = 0;
	private Scanner sc;
	
	/**
	 * Analyses and writes the results to the specified file location.<br>
	 * @param fileDirectory The directory output location for the results of the analysis.
	 * @param lexiconData ConcurrentHashMap containing the data from the provided lexicon for analysis.
	 * @param tweets ConcurrentLinkedDeque containing the parsed data of the files that will be passed for analysis.
	 * @throws Throwable Handles any exceptions thrown to prevent program from crashing. 
	 */

	public void scoreOfTweets(String fileDirectory, Map<String, Double> lexiconData, Collection<String> tweets)
			throws Throwable {

		System.out.println("Enter a name for the file.");
		sc = new Scanner(System.in);
		String saasName = sc.next();
		
		
		try (var threads = new java.util.concurrent.StructuredTaskScope.ShutdownOnFailure()) {
			Map<String, Double> saasMap = new ConcurrentHashMap<String, Double>();
			System.out.println("Executing Please wait.");
			

			Iterator<String> tweetiterator = tweets.iterator(); 
			
			while (tweetiterator.hasNext()) {
				/*
				 * Big O(n) depending on the numbers of tweets to iterate through. Though this time complexity maybe 
				 * impacted from the calling of the summation and writeout methods. 
				 */
				
				String tweet = tweetiterator.next();

				threads.fork(() -> {
					sumCalculation(tweet, lexiconData, saasMap);
					writeOut(fileDirectory, saasName, saasMap);

					return null;
				});

			}
			threads.join();
			threads.throwIfFailed(e -> e);

		}
		System.out.println("Analysis Complete. File written to " + fileDirectory);

	}
	/*
	 * Method to caluclate the sentiment of the tweets. Method converts the tweet to lowercase and splits on the 
	 * whitespace to get the individual word. It then checks if the tweet words are in the lexiconmap to compare and 
	 * generate results.
	 * <p> 
	 * @param tweet Passed as a string of the tweet, it is then split into individual words.
	 * @param lexiconData Map containing the keyvalue pairs from the lexicon file.
	 * @param saasMap Map to hold the result of the analysis. The double in this map holds the sum of the sentiment of each of the words in the tweet.
	 * @throws Exception Throws exception if there is an issue during the method call. 
	 */

	private void sumCalculation(String tweet, Map<String, Double> lexiconData, Map<String, Double> saasMap) throws Exception {
		double wordno = 0;
		Collection<Double> results = new ConcurrentLinkedDeque<Double>();
		
		/*Big O(n) Again based on the number of strings being passed as there is only 1 for loop. The adding to the map is
		 * in constant time operation as it is a concurrent hashmap. 
		 */
		
		for (String str : tweet.replaceAll("[^a-zA-Z]", " ").split("\\s+")) {

			if (lexiconData.containsKey(str)) {
				wordno++;
				results.add(lexiconData.get(str));//Big O(1) constant time to add to map.

			}
		}
		score = Summation.saaS(results);
		setSft(Summation.scoreTotal(results, wordno));
		saasMap.put(tweet, score);

	}

	public double getSft() {
		return sft;
	}

	public void setSft(double sft) {
		this.sft = sft;
	}

}
