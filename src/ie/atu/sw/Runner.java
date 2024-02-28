package ie.atu.sw;


/**
 * @author John Sullivan
 * @version JDK21
 * @version 1.0
 * 
 * <p>Sentiment Analyser using Virtual Threads. To compile using the command line use the 
 * javac --release 21 --enable-preview ie/atu/sw/*.java followed by 
 * java --enable-preview ie.atu.sw.Runner.
 * </p>
 */

public class Runner {
	
	/**
	 * <p>Launches the program. Creates an instance of the menu class for 
	 * the use to input commands and select options. 
	 * </p>
	 * @param No parameters to be passed into the main method. 
	 * @throws Exception handling an error during the launch of the application.
	 */

	public static void main(String[] args) throws Exception {
		try {
			Menu m = new Menu();
			m.start();

		} catch (Exception e) {
			System.out.println("Error Launching Program. " + e.getMessage());
			e.printStackTrace();
		}


    }

}