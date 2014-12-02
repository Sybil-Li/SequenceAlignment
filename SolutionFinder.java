/**
 * File: SolutionFinder.java
 * 
 * A class that takes the memoization matrix and backtrack to find the solution
 * and prints solution in required format to standard output
 *
 * @author Sybil Li
 * @date 11/15/14
 *
 * @see Stack
 */

import java.io.*;
import java.util.Stack;


public class SolutionFinder {

	private Node[][] memo;
	private char[] s1, s2;
	private int numrow, numcolumn;

	/* Constructor Method */
	public SolutionFinder(Node[][] m, char[] S1, char[] S2, int s1len, int s2len)
	{
		memo = m;
		s1 = S1;
		s2 = S2;
		numrow = s1len + 1;
		numcolumn = s2len + 1;
	}

	/** Prints out the best alignment accrding to given solution matrix */
	public void findSolution ()
	{
		int i, j, flag;	

		/* data structure to store array to print */
		Stack<Character> s1_aligned = new Stack();
		Stack<Character> s2_aligned = new Stack();
		Stack<Integer> penalty = new Stack();

		i = numrow - 1;
		j = numcolumn -1;

		while ((i != 0) || (j != 0))
		{
			/* get information for which node procedes current node */
			flag = memo[i][j].getFlag();

			switch(flag)
			{
				case 1: s1_aligned.push('-');
						s2_aligned.push(s2[j]);
						penalty.push((memo[i][j].getTotalp()) - (memo[i][j - 1].getTotalp()));
						j--;
						break;
				case 2: s1_aligned.push(s1[i]);
						s2_aligned.push(s2[j]);
						penalty.push((memo[i][j].getTotalp()) - (memo[i - 1][j -1].getTotalp()));
						i--;
						j--;
						break;
				case 3: s1_aligned.push(s1[i]);
						s2_aligned.push('-');
						penalty.push((memo[i][j].getTotalp()) - (memo[i - 1][j].getTotalp()));
						i--;						
						break;
			}
		}

		/* printing result */
		System.out.println("The best alignment is");
		System.out.println();
		while (!s1_aligned.empty())
			System.out.print(s1_aligned.pop() + " ");
		System.out.print('\n');	
		while (!s2_aligned.empty())
			System.out.print(s2_aligned.pop() + " ");
		System.out.print('\n');	
		while (!penalty.empty())
			System.out.print(penalty.pop() + " ");
		System.out.print('\n');
		System.out.println();
		System.out.println("with the minimum edit distance of " + memo[numrow - 1][numcolumn - 1].getTotalp() + ".");		 
	}
}









