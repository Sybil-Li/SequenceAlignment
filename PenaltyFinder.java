/**
 * File: PenaltyFinder.java
 * 
 * A class to return the associated mismatch penalty for two mismatiched character
 * according to the given matrix
 *
 * @author Sybil Li
 * @date 11/15/14
 */


public class PenaltyFinder {

	private int[][] mismatchPenalty;

	/* Constructor Method */
	public PenaltyFinder(int[][] mismatch)
	{
		mismatchPenalty = mismatch;
	}

	/** 
	 * Find the corresponding mismatch penalty for two mismatiched character 
	 * @param character from sequence1
	 * @param character from sequence2
	 * @return the corresponding mismatch penalty
	 */
	public int findPenalty (char c1, char c2)
	{
		int i = 0, j = 0;		//row, column indices

		/* determine row index */
		if (c1 == 'A')
			i = 0;
		else if (c1 == 'C')
			i = 1;
		else if (c1 == 'G')
			i = 2;
		else
			i = 3;
		
		/* determine column index */
		if (c2 == 'A')
			j = 0;
		else if (c2 == 'C')
			j = 1;
		else if (c2 == 'G')
			j = 2;
		else
			j = 3;

		return mismatchPenalty[i][j];
	}

}







