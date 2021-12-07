package Riddlark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Gameplay implements Runnable {

    public String command;
    private Player player;
    public Socket pSocket;
    public GameStats gameStats;
    private BufferedReader in;
    private PrintWriter out;

    Gameplay(Player player) {
        this.player = player;
        this.pSocket = player.socket;
        this.in = player.in;
        this.out = player.out;
    }

    public void run() {
        try {
            countDown();

            out.println("Answer the riddles as fast as you can, you'll have 30 seconds before time is up...good Luck\n>");

            gameStats = startGame();
            player.setStats(gameStats);

            out.println(player.getStats());

            out.println("Bye bye...");
            in.close();
            System.exit(0);

        } catch (Exception e) {
        }
    }

    public GameStats startGame() throws IOException, InterruptedException {
        GameStats game;
        int qCount = 0;
        int correctGuesses = 0;
        long startTime;
        long nextQuestionTime;
        long[] elapsedTimes = {0, 0, 0, 0, 0};

        while (true) {
            if (qCount == riddles.length) {
                out.println("Congratulations, You have completed the game");
                break;
            }
            String answer = getRiddle(qCount);
            startTime = (System.currentTimeMillis() / 1000);
            nextQuestionTime = startTime + 10000;
            String command = in.readLine();
            if (command.contains("quit")) {
                break;
            }
            if (!command.contains(answer)) {
                elapsedTimes[qCount] = (System.currentTimeMillis() / 1000 - startTime);
                out.println("Sorry, the correct answer was " + answer + "\nYou lost the game");
                break;
            } else if (command.contains(answer)) {
                if ((System.currentTimeMillis() / 1000) > nextQuestionTime) {
                    out.println("Sorry, you didn't answer in time. You have lost the game");
                    break;
                }
                elapsedTimes[qCount] = (System.currentTimeMillis() - startTime * 1000) / 1000;
                out.println("Hooray!!");
                out.println("Answered in " + elapsedTimes[qCount] + " seconds!");
                correctGuesses++;
                qCount++;
                out.println("Wait for the timer to go off for the next question");
                int j = 5;

                for (long i = nextQuestionTime; i > startTime; i -= 1000) {
                    TimeUnit.SECONDS.sleep(1);
                    if (i <= nextQuestionTime - 5000) {
                        out.println(j + " seconds til next question >>");
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

    public String getRiddle(int qCount) {
        out.println(riddles[qCount][0]);
        return riddles[qCount][1];
    }

    private void countDown() throws InterruptedException {
        out.println("Get ready");
        for (int i = 5; i >= 0; i--) {
            out.println("Game starts in ..." + i);
            TimeUnit.SECONDS.sleep(1);
        }
    }

//        final Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new WaitableTimerTask( out, latch) {
//            int i = 5;
//            @Override
//            public void run() {
//                if (i > 0) {
//                    out.println("Game starts in ..." + i--);
//                } else {
//                    latch.countDown();
//                    timer.cancel();
//                }
//            }
//        }, 0, 1000);
//        latch.await();
//    }
}
