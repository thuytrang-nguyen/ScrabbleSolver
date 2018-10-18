// Nguyen Thuy Trang
// Moderation Spring 2018

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;


public class SpellCheck {
    private static String[] words;
    private static String[] testWords = {"purge", "bear", "jackalope", "liger", "zebra"};

    public static void loadWords() {
        // the array is sorted lexicographically
        words = In.readStrings("words.txt");
        Arrays.sort(words);
    }

    // recursive binary search algorithm
    public static boolean binarySearch(String query, int lo, int hi) {
        if (lo <= hi) {
            if (query.compareTo(words[lo + (hi - lo) / 2]) == 0) {
                return true;
            } else if (query.compareTo(words[lo + (hi - lo) / 2]) < 0) {
                return binarySearch(query, lo, (lo + (hi - lo) / 2) - 1);
            } else if (query.compareTo(words[lo + (hi - lo) / 2]) > 0) {
                return binarySearch(query, lo + (hi - lo) / 2 + 1, hi);
            }
        }
        return false;
    }

    public static boolean check(String query) {

        return binarySearch(query, 0, words.length - 1);
    }

    // main method for testing the correctness of program
    public static void main(String[] args) {
        loadWords();
        Stopwatch timer1 = new Stopwatch();
        for (String word : testWords) {
            if (check(word)) {
                StdOut.println("found " + word);
            } else {
                StdOut.println(word + " not found");
            }
        }
        double elapsed1 = timer1.elapsedTime();
        StdOut.println("Time elapsed:  " + elapsed1 + " seconds");
    }
}