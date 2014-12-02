/******************************************************************************
/* Compile and run the program
/* $ javac *.java
/* $ java alignSequence < data.txt(file name that contains the )	
/******************************************************************************

/******************************************************************************
/* Java API used
/*		- Scanner Class
/*		- Stack
/******************************************************************************

/******************************************************************************
/* Data Structure
/*
/* Input Storage
/* 		Mismatch penalty
/* 		- Stored as a 4*4 array of integers
/*		  with 0 --> A, 1 --> C, 2 --> G, 3 --> T
/*		- Corresponding mismatch penalty by direct indexing into the array
/*
/* 		DNA sequence
/*		- Stored in a character array with a space padding at i = 0
/*		- Individual character can be accessed by indexing into the array
/*
/* Node Class
/* 		- Entry typer for memoization matrix
/*		- Has two fields: totalp, flag
/*		- Totalp is the minimum total cost upto the point represented by the 
/*		  position of the Node in the array
/*		- For example, the Node at M[i][j] represents the minimum total edit
/*		  distance for the alignment of the first to ith characters of S1
/*		  and the first to ith characters of S2
/*		- flag is an integer variable whose value represents the predecessor
/*		  of the current array entry, that is, the previous entry that gives
/*		  the current minimum value of totalp
/*		- flag meaning:
/* 			1: minimum = left --> M[i][j-1]
/* 			2: minimum = topleft --> M[i-1][j-1]
/* 			3: minimum = top --> M[i-1][j]
/*
/* Memoization Matrix
/*		- The program uses dynamic programming and uses an (n + 1) * (n + 1)
/*		  array of Nodes (@see Node) to store intermediate solutions
/*		- Each array entry can be accessed by indexing into the array
/*		- Node class has methods defined for accessing its private field
/*
/* PenaltyFinder
/*		- Does not create any additional data structure
/*		- Takes the mismatch penalty matrix as a parameter
/*
/* SolutionFinder
/*		- Creates two stacks to store two aligned sequences, adding '-' when
/*		  necessary, and one more to store corresponding penalty value
/*		- Also takes the memoization matrix and the two original sequences
/*		  as parameters
/*
/******************************************************************************

/******************************************************************************
/* Implementation of Algorithm
/*
/* Memoization matrix
/*		- Implemented using a two dimensional array of Nodes.
/*		- The algorithm first fills in the first row and first column according
/*		  to the value specified in the algorithm description.
/*		- For all entries in the first row, flag value is set to 3, because 
/*		  they follow from the left entry, leaving tj unmatched, since
/*		  row number == 0 implies that s has length 0.
/*		- For all entries in the second row, flag value is set to 1, because 
/*		  they follow from the top entry, leaving si unmatched, since
/*		  column number == 0 implies that t has length 0.
/*		- Then the algorithm starts filling in the rest of the memoization
/*		  array iterating through each row, setting totalp and flag accordingly
/*
/* PenaltyFinder
/*		- A PenaltyFinder is created after the similarity matrix is read.
/*		- This is to modulate the code so that the main algorithm can be
/*		  expressed more concisely.
/*		- It simply return the associated mismatch penalty for two mismatiched 
/*		  character according to the given matrix, taking two characters as
/*		  parameters.
/*		- Constructor requires constant time to read 4*4 similarity matrix.
/*		- findPenalty() requires constant time to find penalty value, since
/*		  determind the row and column number correspoding to the given
/*		  characters and indexing into the array both require only constant
/*		  time.
/*		- Thus this does not affect the running time of the algorithm.
/*
/* SolutionFinder
/*		- After the memoization matrix is entirely filled, a SolutionFinder
/*		  is created.
/*		- Constructor takes the memoization matrix, the original sequences
/*		  s and t, and their lengths as parameters.
/*		- Thus creating an instance takes O(mn) time.
/*		- Creates three additional stacks to store solution.
/*		- Its function is to "translate" the flag value of each entry into two
/*		  characters or a character or a '-', and push them into respective
/*		  stacks to be printed out.
/*		- flag == 1 implies that si is left unmatched.
/*		- flag == 2 implies that si and tj are matched.
/*		- flag == 3 implies that tj is left unmatched.
/*		- Translating takes constant time, and push() also takes constant time.
/*		- The maximum size of each stack is m+n (if no si and tj are matched).
/*		- Thus at most n "translations" are carried out. --> O(m+n) time
/*		- Printing invloves popping stack element one by one until the stack
/*		  is empty.
/*		- Overall, SolutionFinder requires O(mn) time to construct, and
/*		  findSolution() takes O(m+n) time to run.
/*		- This does not increase the time complexity of the algorithm.
/*
/******************************************************************************









