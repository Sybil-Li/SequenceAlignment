/**
 * File: alignSequence.java
 * 
 * A program that takes in two DNA sequence (strings consisting of letters A, C, G, T)
 * a value for gap penanlty and a matrix of values for mismatch penalty
 * and outputs the alignment with minimum edit distance.
 *
 * @author Sybil Li
 * @date 11/17/14
 *
 * @see Node
 * @see PenaltyFinder
 * @see SolutionFinder
 * @see Stack
 * @see Scanner
 */

import java.util.*;
import java.io.*;


public class alignSequence
{
	public static void main (String args[]) throws IOException
	{
		
		/* read data from input */
		Scanner scanner = new Scanner(System.in);

		/* read gap penalty */
		int delta = scanner.nextInt();

		/* save mismatch penalty */
		int[][] mismatch = new int[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				mismatch[i][j] = scanner.nextInt();

		PenaltyFinder pf = new PenaltyFinder (mismatch);
		
		/* save sequence1 */
		String temp = scanner.next();
		temp = " " + temp;
		char[] S1 = temp.toCharArray();
		int lengthS1 = S1.length - 1;
		
		/* save sequence2 */
		temp = scanner.next();
		temp = " " + temp;
		char[] S2 = temp.toCharArray();
		int lengthS2 = S2.length - 1;

		/* algorithm that finds the best alignment */

		/* define and fill up memoization matrix */
		Node[][] M = new Node[lengthS1 + 1][lengthS2 + 1];
		int i, j, penalty;
		for (i = 0; i < lengthS1 + 1; i++)
			M[i][0] = new Node(i * delta, 3);
		for (j = 0; j < lengthS2 + 1; j++)
			M[0][j] = new Node(j * delta, 1);
		for (i = 1; i < lengthS1 + 1; i++) 
		{
			for (j = 1; j < lengthS2 + 1; j++)
			{
				penalty = pf.findPenalty(S1[i], S2[j]);
				/* default minimum = topleft */
				M[i][j] = new Node(penalty + M[i-1][j-1].getTotalp(), 2);
				/* if minimum = left */
				if ((delta + M[i][j - 1].getTotalp()) < M[i][j].getTotalp())
				{
					M[i][j].setTotalp(delta + M[i][j - 1].getTotalp());
					M[i][j].setFlag(1);
				}
				/* if minimum = top */
				if ((delta + M[i - 1][j].getTotalp()) < M[i][j].getTotalp())
				{
					M[i][j].setTotalp(delta + M[i - 1][j].getTotalp());	
					M[i][j].setFlag(3);
				}		
			}
		}

		/* Compute actual alignment using a SolutionFinder */
		SolutionFinder solution = new SolutionFinder(M, S1, S2, lengthS1, lengthS2);
		solution.findSolution();
	}
}











