// Nguyen Thuy Trang
// Moderation Spring 2018

public class Solution implements Comparable<Solution> {

    private String word;    // the given word 
    private int score;      // the total score of the word 

    // the alphabet for asigning point values for each letter
    private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    // the points assigned for a given letter
    private final static int[] value = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    // the constructor creates a Solution object given an input String and calculates its score
    public Solution(String w) {

        this.score = 0;
        this.word = w;

        for (int i = 0; i < this.word.length(); i++) {
            for (int o = 0; o < alphabet.length(); o++) {
                if(alphabet.indexOf(this.word.charAt(i)) < 0){
                    throw new IllegalArgumentException("Use of special characters and numbers not allowed!");
                }
                else{
                    if(this.word.charAt(i) == alphabet.charAt(o)) {
                        this.score += value[o];
                    }
                }
            }
        }
    }

    // this method overrides the compare method and compares two tiles by comparing their score
    public int compareTo(Solution s) {

        if (s.score == this.score) {
            return 0;
        } else if (s.score < this.score) {
            return 1;
        } else {
            return -1;
        }
    }   

    // this method overrides toString() method and returns the word of the Solution
    public String toString() {
        return this.word;
    }

    // this method returns the score of the Solution
    public int getTotalScore() {
        return this.score;
    }

    // this method returns the value of a letter at index k
    public int getScoreAt(int k) {
        if (k > word.length() - 1 || k < 0) {
            System.err.println("index out of bounds");
        }
        for (int o = 0; o < alphabet.length(); o++) {
            if (this.word.charAt(k) == alphabet.charAt(o)) {
                return value[o];
            }
        }
        return -1;
    }

}
