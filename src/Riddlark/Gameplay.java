package Riddlark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gameplay {

    public static String command;
    public static GameStats gameStats;

    public static void main(String[] args) throws IOException {

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
            }

        }
        System.out.println("Name: player.getUname()"
                + "\nCorrect Guesses: " + gameStats.getCorrectGuesses()
                + "\nTime taken to answer last riddle: " + gameStats.getLastElapsedTime());

        System.out.println("Bye bye...");
    }

    public static GameStats startGame(BufferedReader keyboard) throws IOException {
        GameStats game;
        int qCount = 0;
        int correctGuesses = 0;
        long startTime;
        long[] elapsedTimes = new long[5];

        while (true) {
            if (qCount == riddles.length) {
                System.out.println("Congratulations, You have completed the game");
                break;
            }
            String answer = getRiddle(qCount);
            startTime = System.currentTimeMillis();
            String command = keyboard.readLine();
            if (command.contains("quit")) {
                break;
            } else if (command.contains(answer)) {
                elapsedTimes[qCount] = (System.currentTimeMillis() - startTime) / 1000;
                System.out.println("Hooray!!");
                System.out.println("Answered in " + elapsedTimes[qCount] + " seconds!");
                System.out.println(elapsedTimes[elapsedTimes.length - 1]);
                System.out.println(elapsedTimes.length);
                correctGuesses++;
                qCount++;

            } else {
                System.out.println("Sorry, the correct answer was " + answer + "\n You lost the game");
                break;
            }
        }
        game = new GameStats(correctGuesses, elapsedTimes);
        return game;
    }

    static String riddles[][] = {
        {"What is always in fron/t of you but can’t be seen?",
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

//        final Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            int i = 30;
//            public void run() {
//                i--;
//                if (i< 0){
//                    timer.cancel();
//                }
//            }
//        }, 0, 1000);
