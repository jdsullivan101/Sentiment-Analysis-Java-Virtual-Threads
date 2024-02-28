package ie.atu.sw;

import java.io.*;

import java.util.*;

/**
 * Writes the results to the specified directory. Contains the synchronized methods
 * in order to handle the concurrency when dealing with virtual threads.  
 */
public class OutWriter {
	
	/**
	 * Synchronized method in order to handle the concurrency of the virtual threads in the Sentiment Analysis Class.
	 * Takes 3 parameters in order to write the file. 
	 * @param outDirectory Gives the location to write the file to.
	 * @param name Passes the name of the file to the method.
	 * @param mapResults Map holds the results of the sentiment analysis. String represents the tweets and Double is for the sum of the sentiment. 
	 * @throws Exception Handles exceptions thrown during the file writing process. 
	 */

	public synchronized void writeOut(String outDirectory, String name, Map<String, Double> mapResults) throws Exception {
		//Big O(1) as file writing has constant time operation. 
		String outFile = outDirectory + File.separator + name + ".txt";

		try {
			writeFile(outFile, mapResults);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private synchronized void writeFile(String outFile, Map<String, Double> map) throws IOException {
		try (FileWriter fw = new FileWriter(outFile)) {
			//Big O(n) to iterate through the map.
			for (Map.Entry<String, Double> printmap : map.entrySet()) {

				fw.write(printmap.getKey() + " : " + printmap.getValue() + "\n");
			}

		}

	}


}
