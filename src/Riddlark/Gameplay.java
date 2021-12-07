package Riddlark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Gameplay {

    public static String command;
    private static Player player;
    private static Socket pSocket;
    public static GameStats gameStats;

    Gameplay(Player player) {
        this.player = player;
        this.pSocket = player.getSocket();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Welcome to Riddlark. "
                + "\nType 'ready' to begin playing or'quit' to stop playing"
                + "\nAnswer the riddles as fast as you can, you'll have 30 seconds before time is up...good Luck\n>");

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            command = keyboard.readLine();

            if (command.contains("quit")) {
                break;
            }

            if (command.contains("ready")) {
                gameStats = startGame(keyboard);
                break;
            } else {
                System.out.println("Input not recognized...Try again");
            }

        }
        System.out.println("Name: player.getUname()"
                + "\nCorrect Guesses: " + gameStats.getCorrectGuesses()
                + "\nTime taken to answer last riddle: " + gameStats.getLastElapsedTime() + " seconds");

        System.out.println("Bye bye...");
        keyboard.close();
        System.exit(0);
    }

    public static GameStats startGame(BufferedReader keyboard) throws IOException, InterruptedException {
        GameStats game;
        int qCount = 0;
        int correctGuesses = 0;
        long startTime;
        long nextQuestionTime;
        long[] elapsedTimes = {0, 0, 0, 0, 0};
        Timer timer = new Timer();

        while (true) {
            if (qCount == riddles.length) {
                System.out.println("Congratulations, You have completed the game");
                break;
            }
            String answer = getRiddle(qCount);
            startTime = (System.currentTimeMillis() / 1000);
            nextQuestionTime = startTime + 10000;
            String command = keyboard.readLine();
            if (command.contains("quit")) {
                break;
            }
            if (!command.contains(answer)) {
                elapsedTimes[qCount] = (System.currentTimeMillis() / 1000 - startTime);
                System.out.println("Sorry, the correct answer was " + answer + "\nYou lost the game");
                break;
            } else if (command.contains(answer)) {
                elapsedTimes[qCount] = (System.currentTimeMillis() - startTime * 1000) / 1000;
                System.out.println("Hooray!!");
                System.out.println("Answered in " + elapsedTimes[qCount] + " seconds!");
                correctGuesses++;
                qCount++;
                System.out.println("Wait for the timer to go off for the next question");
                int j = 5;

                for (long i = nextQuestionTime; i > startTime; i -= 1000) {
                    TimeUnit.SECONDS.sleep(1);
                    if (i <= nextQuestionTime - 5000) {
                        System.out.println(j + " seconds til next question >>");
                        j--;
                    }
                }
            }
        }
        game = new GameStats(correctGuesses, elapsedTimes);
        return game;
    }

    static String riddles[][] = {
        {"What is always in front of you but can’t be seen?",
            "future"
        },
        {"The more of this there is, the less you see. What is it?",
            "darkness"
        },
        {"What can you keep after giving to someone?",
            "Your word"
        },
        {"I have branches, but no fruit, trunk or leaves. What am I?",
            "bank"
        },
        {"What is full of holes but still holds water?",
            "A sponge"
        }
    };

    public static String getRiddle(int qCount) {
        System.out.println(riddles[qCount][0]);
        return riddles[qCount][1];
    }

}
