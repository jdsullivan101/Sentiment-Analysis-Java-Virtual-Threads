package ie.atu.sw;

import java.util.Collection;

/**
 * </p>
 * Interface that contains the methods to calculate the sentiment of the tweets.
 * Using Sum as Score and Score from Total. 
 * Method can be updated in the future with more sentiment analysis equations as needed. 
 * </p>
 */
public interface Summation{
	
	/**
	 * Sum as score method. Calculates sentiment as (positive - negative). 
	 * @param data Collection of doubles that are passed from the sentiment analysis class. 		
	 * @return a double that contains the sum of the collection of doubles.
	 */

    public static double saaS(Collection<Double> data){
    	//Big O(n) as it is streaming data from a concurrent linked deque
        double scoreandSum = (data.stream().mapToDouble(d -> d).sum());
		return scoreandSum;

    }
    
    /**
     * <p>Score from total method. Calculates sentiment as (positive - negative)/(total words).
     * Contains a recursive case to prevent a situation where there is no word found in lexicon and 
     * prevents a division by 0. 
     * </p>
     * @param data Collection of doubles of the 
     * @param wordno incremented from the sentiment analysis based on the number of words that are contained in lexicon and tweets.
     * @return Double containing the value of the score from total method.
     */

    public static double scoreTotal(Collection<Double> data, double wordno){
    	
    	//Big O(n) same reason as method above. 
        double basecase = 1;
        double scorefromTotal = (data.stream().mapToDouble(d -> d).sum());
		if(wordno == 0) {
            return (scorefromTotal /basecase);
        }else{
            return (scorefromTotal/wordno);
        }
    }

	
}
