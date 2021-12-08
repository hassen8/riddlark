package Riddlark;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Group {

    int id;
    int playerNo = 0;
    public static ArrayList<Gameplay> game = new ArrayList<>();
    boolean isFull = false;
    int readyPlayers = 0;
    boolean inSession = false;
    Player[] players = new Player[4];
    static Player[] spectators = new Player[4];
    BufferedReader in;
    PrintWriter out;
    static int spectNo = 0;
    private static ExecutorService pool = Executors.newFixedThreadPool(30);

    public Group(int id, Player player) {
        this.id = id;
        addPlayer(player);
    }

    public void addPlayer(Player player) {
        player.setgId(id);
        if (playerNo == 0) {
            players[0] = player;
            playerNo++;
        } else {
            players[playerNo] = player;
            playerNo++;
            if (playerNo > 3) {
                isFull = true;
            }

        }

    }

    public static void addSpectator(Player spectator) {
        if (spectNo == 0) {
            spectators[0] = spectator;
            spectators[0].out.println("You are now in spectator mode, you'll receive the riddles and answers, and player answers\n");
            spectNo++;
        } else {
            spectators[spectNo] = spectator;
            spectators[spectNo].out.println("You are now in spectator mode, you'll receive the riddles and answers, and player answers\n");
            spectNo++;
        }
    }

    public static void sendRiddleToSpectator(Riddle riddle) {
        if (spectNo > 0) {
            for (int i = 0; i < spectNo; i++) {
                spectators[i].out.println("Riddle: " + riddle.riddle);
                spectators[i].out.println("Answer: " + riddle.answer);
            }
        }
    }

    public static void sendPlayerAnswer(String pName, String answer) {
        if (spectNo > 0) {
            for (int i = 0; i < spectNo; i++) {
                spectators[i].out.println(pName + ": " + answer);

            }
        }
    }

    public static void sendWinnerToSpectators(String winner) {
        if (spectNo > 0) {
            for (int i = 0; i < spectNo; i++) {
                spectators[i].out.println("The winner is: " + winner);

            }
        }
    }

    public static void sendPlayerLost(String loser) {
        if (spectNo > 0) {
            for (int i = 0; i < spectNo; i++) {
                spectators[i].out.println("Player: " + loser + " lost the game...");
            }
        }
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isInSession() {
        return inSession;
    }

    public void incReadyPlayers() {
        readyPlayers++;
        if (readyPlayers >= 2) {
            if (readyPlayers == playerNo) {
                inSession = true;
                playGame();
            }
        }
    }

    public void playGame() {
        for (int i = 0; i < playerNo; i++) {
            Gameplay nGame = new Gameplay(players[i]);
            game.add(nGame);
        }

        game.forEach(gameInstance -> {
            pool.execute(gameInstance);
        });
    }

}
