/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Riddlark;

/**
 *
 * @author hsn
 */
public class GameStats {

    int correctGuesses;
    long[] elapsedTimes;

    public GameStats(int correctGuesses, long[] elapsedTimes) {
        this.correctGuesses = correctGuesses;
        this.elapsedTimes = elapsedTimes;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public long getLastElapsedTime() {
        if (correctGuesses == 0) {
            return elapsedTimes[correctGuesses];
        } else {
            return elapsedTimes[correctGuesses - 1];
        }
    }

}
