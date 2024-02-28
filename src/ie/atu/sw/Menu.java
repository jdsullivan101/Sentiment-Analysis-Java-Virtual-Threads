package ie.atu.sw;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

/**
 * Menu class to handle user input and direction of the program.
 * Creates an instance of the DirectorySelection class as this is the most used
 * class of the program.  
 */

public class Menu {
	
	
	private DirectorySelection d;
	private LexiconMap lmap;
	private SentimentAnalysis sen;
	private String lexicon, fileinput, analysisoutput;
	private Collection<String> tweetdata;
	private Map<String, Double> lexmap;
	private boolean keepRunning = true;
	

	/**
	 * Starts the application. User to select the operations of the program. 
	 * @throws Exception Handles exceptions thrown by user inputs to prevent program crashing.
	 */
	
	public Menu() {
		this.d = new DirectorySelection();
		this.lmap = new LexiconMap();
		this.sen = new SentimentAnalysis();
		
	}

	public void start() throws Exception {

		try (Scanner scanner = new Scanner(System.in)) {

			while (keepRunning) {

				try {
					showMenu();

					int choice = Integer.parseInt(scanner.next());
					switch (choice) {
					case 1 -> inputDirectory();
					case 2 -> outDirectory();
					case 3 -> selectLexicon();
					case 4 -> configureLex();
					case 5 -> parseTweets();
					case 6 -> sentimentExecute();
					case 7 -> options(); // Launches the options menu.
					case 8 -> keepRunning = false; // End the program.
					default -> System.out.println("Invalid Selection. Please Select Between 1 & 8");

					}

				} catch (Throwable e) {
					// Catching incorrect inputs by the user.
					e.printStackTrace();
					System.out.println("Cannot Accept Non-Numeric Values. " + e.getMessage());
				}

			}
		}
		// Closing the program after selecting the quit option.
		System.out.println("Closing Program, Goodbye.");
		System.exit(0);

	}

	/*
	 * Configures the lexicon into a ConcurrentHashMap. 
	 */

	private void configureLex() throws Exception {
		
		lexmap = lmap.configureLexicon(lexicon);
		System.out.println("The number of words in the lexicon is: " + lexmap.size());
	}
	
	/*
	 * Gets the pathway to the lexicon file defined by the user. 
	 */
	private void selectLexicon() throws Exception {
		lexicon = d.lexiconPath();
	}
	
	/*
	 * Gets the input directory of the tweets.
	 */
	private void inputDirectory() throws Exception {
		fileinput = d.directorySelection();

	}
	
/*
 * Gets the output directory to write the results of the analysis to. 
 */
	private void outDirectory() throws Exception {
		analysisoutput = d.directorySelection();
	}
	
	/*
	 * Launches the options menu for the user. 
	 */

	private void options() throws Exception {
		OptionsMenu om = new OptionsMenu();
		om.launch();

	}
	
	/*
	 * Parses the files holding the data, and puts them into a ConcurrentLinedDeque. 
	 */

	private void parseTweets() throws Exception {
		try {
			FileParser ps = new FileParser();
			tweetdata = ps.parse(fileinput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
/*
 * Performs the analysis of the tweets, by passing the 
 * 
 */
	private void sentimentExecute() throws Throwable {
		if (analysisoutput != null && lexmap != null && tweetdata != null) {
			sen.scoreOfTweets(analysisoutput, lexmap, tweetdata);
		} else {
			System.out.println("Cannot proceed, check your inputs.");
			System.out.println("Out directory: " + analysisoutput.toString());
			System.out.println("Lexicon has: " + lexmap.size());
			System.out.println("The tweets has: " + tweetdata.size());
		}
	}
	

	private void showMenu() throws Exception {
		System.out.println(ConsoleColour.PURPLE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*             Virtual Threaded Sentiment Analyser          *");
		System.out.println("*                                                          *");
		System.out.println("*                         G00425758                        *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify a Text File.");
		System.out.println("(2) Select an output directory.");
		System.out.println("(3) Select Lexicon.");
		System.out.println("(4) Configure Lexicon.");
		System.out.println("(5) Parse tweets.");
		System.out.println("(6) Analyse and Report Sentiment.");
		System.out.println("(7) Options");
		System.out.println("(8) Quit");
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-8]>");
		System.out.println();
		System.out.print(ConsoleColour.BLUE_BOLD_BRIGHT);

	}

}
