# ScrabbleSolver
This program displays a highest Scrabble scored solution from a word scramble. It uses algs4 (algs4.cs.princeton.edu) library.

Compile:javac -cp .:algs4.jar *.java

Run: java -cp .:algs4.jar ScrabbleSolver <letter_scramble> <OPTIONAL: letter to include - has to be in the letter_scramble>

Running Example: java -cp .:algs4.jar ScrabbleSolver egersdk r

This will display a best solution including "r" from the world scramble: "reeks" with score of 9.


INTRODUCTION:
Given a scramble of letters the program looks for words that one can create using those letters with the highest score outcome. Since in the Scrabble game each letter has a certain value attached to it, the program yields the solution with the highest total score calculated by adding up the values of the letters used. Having played Scrabble multiple times, I thought that it would be very useful to have a helper/cheating program for the game. The program can either find a general solution from a given word scramble or find a solution using a certain letter of choice. The option of using a letter is very practical in the game as the players have to place the tiles on the board at specific locations (they often have to combine their tiles with already existing tiles on the board). The program consists of four classes: SpellCheck, anagramSolver, Solution and ScrabbleSolver. The code for the first two classes comes from a laboratory assignment from the Data Structures course. For my project, I created the Solution and ScrabbleSolver class and modified the anagramSolver class.

DESIGN:
The SpellCheck class uses a binary search algorithm to verify if a given input word exists in the dictionary file. The anagramSolver program takes a word scramble and tries to find actual words that can be formed by using all of the letters in the scramble. The anagramSolver changes the order of the letters in the scramble in all possible ways and then uses the SpellCheck class to check if the reordered word exists. I modified the anagramSolver class to save the solutions in a max priority queue with a heap implementation. This allowed me to store the best solution at the top of the heap in form of Solution objects since I overwrote the Comparable interface for the Solution class. I created the Solution class in order to be able to compare the generated words by the number of points amounted for them. It also contains helpful methods for the visualization of the ScrabbleSolver program. The ScrabbleSolver uses the anagramSolver to find the solution with the highest score. In order to do that, I came up with an algorithm to generate subsets of the letters from the word scramble and find solutions for the given subsets. I used an ArrayList of chars and multiple forloops in order to find solutions for subsets of size n till n-4 (where n is the size of the ArrayList of chars - letters). I refrained myself to looking for solutions up to size n-4 as my goal was to find a solution with the highest score. The main method of the ScrabbleSolver also visualizes the result using the algs4 graphics library.

RUNTIME BEHAVIOR:
Since the the runtime of general solution and solution with a condition letter turned out to be very similar (this is due to the fact that we just keep deleting the max value from the top of our priority queue until we find an answer that contains the demanded letter), then I decided to only time the process of getting the general solution. I timed input of length 2 up till length of 10 and for each length group I picked 5 different word scrambles. 
The time function does not seem to follow a typical order of growth. Nonetheless, since the program allows maximum 10 letters in the input, for input of length 2 to 8 it takes less than half of a second for a solution to be generated. For input of length 9, it takes 1.2 seconds on average and for input of length 10 - around 8 seconds to find a solution. This shows that the program is rather efficient for its purpose.

