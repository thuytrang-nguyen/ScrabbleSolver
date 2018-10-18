// Nguyen Thuy Trang
// Moderation Spring 2018

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class anagramSolver {

    // the max priority queue (with binary heap) to store all the scrabble solutions
    // I used the maximum priority queue to place the word with a highest score at the top of the heap
    public MaxPQ<Solution> solutions_;  

    // constructor sets the initial capacity of the heap to 50
    public anagramSolver() {
        solutions_ = new MaxPQ<>(50);
        SpellCheck.loadWords();
    }

    // reorders the letters of the scramble in every possible sequence and checks if the reordered word exists in the dictionary
    // add the solutions to the MaxPQ 
    public void perms(String p, String w) {
        String i = "";
        if (w.equals(i) && SpellCheck.check(p) == true) {
            // add the word to the MaxPQ as the Solution object
            solutions_.insert(new Solution(p));
        }
        else {
            for (int k = 0; k < w.length(); k++) {
                perms(p + w.charAt(k), w.substring(0, k) + w.substring(k + 1, w.length()));
            }
        }
    }

    // solve the anagram
    // start the recursive perms 
    public void anSolver(String w) {
        perms("", w);
    }

    // this method returns the best solution for the ScrabbleSolver
    public Solution ret1() {
        if (solutions_.isEmpty()){
            return null;
        }else{
            return solutions_.delMax();
        }
    }

    // this method returns the best solution for the ScrabbleSolver with letter x
    public Solution ret2(char x){
        if (solutions_.isEmpty()){
            return null;
        }else{
            Solution sol = solutions_.delMax();
            String stringSol = sol.toString();
            while (stringSol.indexOf(x) < 0){
                sol = solutions_.delMax();
                stringSol = sol.toString();
            }
            if (stringSol.indexOf(x) >= 0){
                return sol;
            }else{
                return null;
            }
        }
    }

    /* --------------------------------------------------------------------------------------------

    methods and main function for testing the correctness of regular anagram solver's algorithms */

    // instead of adding the solutions to the MaxPQ, this perms prints out the solution
    public static void perms2(String p, String w) {
        String i = "";
        if (w.equals(i) && SpellCheck.check(p) == true) {
            System.out.println(p);
        } else {
            for (int k = 0; k < w.length(); k++) {
                perms2(p + w.charAt(k), w.substring(0, k) + w.substring(k + 1, w.length()));
            }
        }
    }

    // solve the anagram
    // start the recursive perms2
    public static void anSolver2(String w) {
        perms2("", w);
    }

     public static void main(String[] args) {
        SpellCheck.loadWords();
        String[] anG = new String[]{"gurpe", "granama", "nelir", "notair", "bahcle", "cat"};
        Stopwatch timer1 = new Stopwatch();
        for (String w : anG) {
            anSolver2(w);
        }
        double elapsed1 = timer1.elapsedTime();
        StdOut.println("Time elapsed:  " + elapsed1 + " seconds");
    }
}

