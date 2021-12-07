package Riddlark;

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
