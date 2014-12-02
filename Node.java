/**
 * File: Node.java
 * 
 * A node class for memoization matrix entry
 *
 * @author Sybil Li
 * @date 11/16/14
 */


public class Node {

	private int totalp;
	private int flag;
	/* flag usage */
	/* 1: minimum = left */
	/* 2: minimum = topleft */
	/* 3: minimum = top*/

	/* constructor */
	public Node(int p, int f)
	{
		totalp = p;
		flag = f;
	}

	/** Returns total penalty */
	public int getTotalp() { return totalp; }

	/** Returns flag value */
	public int getFlag() { return flag; }

	/** 
	 * Set total penalty to a new value
	 * @param p new value of total penalty
	 */
	public void setTotalp(int p) { totalp = p; }

	/** 
	 * Set flag to a new value
	 * @ p new value of total penalty
	 */
	public void setFlag(int f) { flag = f; }
}




