package ie.atu.sw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class for the selection of the directory location of the input directory, output directory and 
 * the location of the lexicon files.
 */
public class DirectorySelection {

	private Scanner sc;
	
	/**
	 * Method to return the location of the directory pathway. <br>
	 * @return String of the pathway to directory.
	 * @throws Exception Handles exceptions for the case of an IOException. 
	 */
	public String directorySelection() throws Exception {

		
		sc = new Scanner(System.in);
		
		boolean b = false;
		String inputDirectory = null;

		
		do {
			System.out.println("Write Your Pathway as path/to/directory");
			System.out.println("Specify the Pathway to Input Directory: ");
			
			inputDirectory = sc.nextLine();
			
			Path path = Paths.get(inputDirectory);
			b = checkPathway(path); 

		} while (!b);

		return inputDirectory; 

	}
	
	/**
	 * Method for the location of the lexicon file. The method is not capable of handling a directory pathway.
	 * User to define pathway as path/to/file/filname.txt
	 * @return String as location of the lexicon file.
	 * @throws Exception In case of error with pathway to directory.
	 */

	public String lexiconPath() throws Exception {
		String lexiconpath;
		System.out.println("Specify location of your lexicon.");
		System.out.println("Write the pathway as path/to/lexicon/file.txt.");
		sc = new Scanner(System.in);
		lexiconpath = sc.nextLine();

		System.out.println("You have selected: " + lexiconpath);
		return lexiconpath;

	}

	private boolean checkPathway(Path userspecified) {
		// Method to check if it is a directory.
		if (Files.isDirectory(userspecified)) {
			System.out.println("Specified Directory is: " + userspecified);
			return true;

		} else {
			// If directory doesn't exist, method returns a false and user can try again.
			System.out.println("Directory Does not Exist. Try Again.");
			return false;
		}

	}
}
