// Nguyen Thuy Trang
// Moderation Spring 2018

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;


public class ScrabbleSolver {

    private String word_; // the letter scramble

    private ArrayList<Character> set_of_chars; // the set of chars available from the letter scramble

    public Solution initial_;   // letter scramble in the Solution form for display purposes

    public Solution solution_;  // the best solution generated

    private final anagramSolver an = new anagramSolver();   // anagram solver for finding solutions


    // the first constructor initializes class variables and finds the solution by calling the method getSolution1()
    public ScrabbleSolver(String letters) {
        word_ = letters;
        if (word_.length() < 2 || word_.length() > 10) {
            throw new IllegalArgumentException("Invalid input");
        }
        set_of_chars = new ArrayList<Character>();

        for (int i = 0; i < word_.length(); i++) {
            set_of_chars.add(word_.charAt(i));
        }
        initial_ = new Solution(word_);
        solution_ = getSolution1();
    }

    // the second constructor initializes class variables and finds the solution containing letter x
    // it uses the other method getSolution2()
    public ScrabbleSolver(String letters, char x) {
        word_ = letters;
        if (word_.length() < 2 || word_.length() > 10) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (word_.indexOf(x) < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        set_of_chars = new ArrayList<Character>();

        for (int i = 0; i < word_.length(); i++) {
            set_of_chars.add(word_.charAt(i));
        }
        initial_ = new Solution(word_);
        solution_ = getSolution2(x);
    }

    // helper function - creates a string out of a char type ArrayList
    public static String createString(ArrayList<Character> p) {
        String o = "";
        for (int i = 0; i < p.size(); i++) {
            o += p.get(i);
        }
        return o;
    }

    // this method generates solutions for the given set of chars and returns them in a form of one concatenated string
    // since we want to get the best solution with the highest score, we restrain ourselves to find solutions up to
    // length n-4 (where n is the number of letters available)

    public void generateSolutions() {

        // len is the total number of the chars available
        int len = word_.length();
        // try to find a solution which uses every single letter
        an.anSolver(this.word_);

    /* algorithm for getting the solutions of size n-1 (where n is the length of the words scramble)
        1. at first iteration remove first letter A
        2. find solutions without A  
        3. add the removed first letter to the end of ArrayList
        4. at second iteration the forloop will again remove the first letter from ArrayList which is now B
        5. find solutions without B
        6. then add B back again to the end and remove first letter C
        7. find solutions without C  
        8. add C back and get rid of next first letter
        ... continue till every single letter was removed (as the loop runs for n times)
    */

        // try to find solutions of length n-1
        if (set_of_chars.size() > 2) {
            for (int i = 0; i < len; i++) {
                char out = set_of_chars.get(0);
                set_of_chars.remove(0);
                an.anSolver(createString(set_of_chars));
                set_of_chars.add(out);
            }
        }

    /* algorithm for getting the solutions of size n-x (where n is the length of the words scramble)
        - instead of having a single forloop, we will have x forloops
        - example (x = 2):
            1. in the first forloop get rid of first letter in ArrayList - A
            2. in the second forloop get rid of the new first letter B 
            3. find solutions without A and B
            4. add the removed B back to the end of ArrayList
            5. at second iteration  of the inner forloop again remove the first letter from ArrayList which is now C
            6. find solutions without A and C 
            7. then add C back again to the end and remove next first letter and find solution   
                ... continue till every single letter from the ArrayList without A was removed (as the loop runs for n-1 times)
                and find all solutions without A and K (where K is a char from the ArrayList without A)
            8. now add A back to the end of ArrayList
            9. in the second iteration of the first forloop we will get rid of the new first letter B
           10. in the inner for loop find all solutions without B and M (where M is a char from the ArrayList without B)
                ... continue the outer loop for all letters from the ArrayList (runs n times)
    */

        // try to find solutions of length n-2
        if (set_of_chars.size() > 3) {
            for (int u = 0; u < len; u++) {
                char out1 = set_of_chars.get(0);
                set_of_chars.remove(0);
                for (int t = 0; t < len - 1; t++) {
                    char out2 = set_of_chars.get(0);
                    set_of_chars.remove(0);
                    an.anSolver(createString(set_of_chars));
                    set_of_chars.add(out2);
                }
                set_of_chars.add(out1);
            }
        }

        // try to find solutions of length n-3
        if (set_of_chars.size() > 4) {
            for (int u = 0; u < len; u++) {
                char out1 = set_of_chars.get(0);
                set_of_chars.remove(0);
                for (int t = 0; t < len - 1; t++) {
                    char out2 = set_of_chars.get(0);
                    set_of_chars.remove(0);
                    for (int l = 0; l < len - 2; l++) {
                        char out3 = set_of_chars.get(0);
                        set_of_chars.remove(0);
                        an.anSolver(createString(set_of_chars));
                        set_of_chars.add(out3);
                    }
                    set_of_chars.add(out2);
                }
                set_of_chars.add(out1);
            }
        }

        // try to find solutions of length n-4
        if (set_of_chars.size() > 5) {
            for (int u = 0; u < len; u++) {
                char out1 = set_of_chars.get(0);
                set_of_chars.remove(0);
                for (int t = 0; t < len - 1; t++) {
                    char out2 = set_of_chars.get(0);
                    set_of_chars.remove(0);
                    for (int l = 0; l < len - 2; l++) {
                        char out3 = set_of_chars.get(0);
                        set_of_chars.remove(0);
                        for (int j = 0; j < len - 3; j++) {
                            char out4 = set_of_chars.get(0);
                            set_of_chars.remove(0);
                            an.anSolver(createString(set_of_chars));
                            set_of_chars.add(out4);
                        }
                        set_of_chars.add(out3);
                    }
                    set_of_chars.add(out2);
                }
                set_of_chars.add(out1);
            }
        }
    }

    // find the general solution
    public Solution getSolution1(){
        generateSolutions();
        return an.ret1();
    }

    // find the solution containing char k
    public Solution getSolution2(char k){
        generateSolutions();
        return an.ret2(k);
    }

    public Solution returnSolution(){
        return this.solution_;
    }

    public static void main(String[] args) {

        int no_Solutions = 0;        // number of initial letters
        int sol_Solutions = 0;       // number of solution letters

        // set up the canvas, scale and pen colors for the visualization
        StdDraw.setCanvasSize(700, 200);    
        StdDraw.setXscale(0, 700);
        StdDraw.setYscale(0, 200);
        Font font1 = new Font("Courier", Font.BOLD, 22);
        Font font2 = new Font("Arial", Font.BOLD, 16);
        Font font3 = new Font("Arial", Font.ITALIC, 12);
        Font font4 = new Font("Arial", Font.PLAIN, 14);
        StdDraw.setFont(font1);
        StdDraw.setPenColor(new Color(0, 102, 0));
        StdDraw.textLeft(30, 180, "Scrabble solver");
        Color black = new Color(0, 0, 0);
        StdDraw.setPenColor(black);

        // make sure that the argument input is of length 1 or 2
        if (args.length < 1 || args.length > 2) {
            System.err.println("Wrong argument size: Please enter a single string or a string with a single char");
            System.exit(0);
        }
        // if it is of length 2, make sure the second input is a single letter
        if (args.length == 2 && args[1].length() != 1) {
            System.err.println("Wrong argument size: Please enter a single string or a string with a single char");
            System.exit(1);
        }

        no_Solutions = args[0].length();    // number of initial letters

        ScrabbleSolver k;     

        // timer for counting the time of generating a solution
        Stopwatch timer = new Stopwatch();  

        // find the best solution containing the letter given in argument
        if (args.length == 2 && args[1].length() == 1){
            k = new ScrabbleSolver(args[0], args[1].charAt(0));
        }    
        // find the general best solution
        else{
            k = new ScrabbleSolver(args[0]);
        } 
      
        if (k.solution_ == null){
            System.out.println("No solutions found");
            System.exit(2);
        }
        // print out the solution and elasped time
        System.out.println(k.solution_);
        double elapsed = timer.elapsedTime();
        StdOut.println("Time elapsed:  " + elapsed + " seconds");

        sol_Solutions = k.solution_.toString().length(); // number of solution letters

        Color t = new Color(255, 230, 179);

        // drawing the initial scrabble Solutions
        for (int i = 0; i < no_Solutions; i++) {
            StdDraw.setPenColor(t);
            StdDraw.filledSquare(70 + i * 40, 125, 20);
            StdDraw.setFont(font2);
            StdDraw.setPenColor(black);
            StdDraw.square(70 + i * 40, 125, 20);
            StdDraw.text(70 + i * 40, 125, String.valueOf(k.initial_.toString().charAt(i)));
            StdDraw.setFont(font3);
            StdDraw.text(85 + i * 40, 109, String.valueOf(k.initial_.getScoreAt(i)));
        }
        // drawing the solution Solutions
        for (int m = 0; m < sol_Solutions; m++) {
            StdDraw.setPenColor(t);
            StdDraw.filledSquare(70 + m * 40, 50, 20);
            StdDraw.setFont(font2);
            StdDraw.setPenColor(black);
            StdDraw.square(70 + m * 40, 50, 20);
            StdDraw.text(70 + m * 40, 50, String.valueOf(k.solution_.toString().charAt(m)));
            StdDraw.setFont(font3);
            StdDraw.text(85 + m * 40, 34, String.valueOf(k.solution_.getScoreAt(m)));
        }

        StdDraw.setFont(font4);
        StdDraw.textLeft(543, 130, "Best word: " + k.solution_.toString());
        StdDraw.textLeft(543, 110, "Total score: " + k.solution_.getTotalScore());

    }

}