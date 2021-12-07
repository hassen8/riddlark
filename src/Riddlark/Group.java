package Riddlark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Group {

    int id;
    int playerNo = 0;
    public ArrayList<Gameplay> game;
    boolean isFull = false;
    int readyPlayers = 0;
    boolean isReady = false;
    boolean inSession = false;
    Player[] players = new Player[4];
    private static ExecutorService pool = Executors.newFixedThreadPool(30);

    public Group(int id, Player player) {
        System.out.println("New Group Created");
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
                System.out.println("Group is full");
                isFull = true;
            }

        }

    }

    public void startGame() {
        setInSession(true);
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void incReadyPlayers() throws IOException {
        readyPlayers++;
        
        if (readyPlayers == playerNo) {
            isReady = true;
            inSession = true;
            playGame();
            
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public boolean isInSession() {
        return inSession;
    }

    public void setInSession(boolean inSession) {
        this.inSession = inSession;
    }

    public void playGame() throws IOException {
        for (Player player : players) {
            game.add(new Gameplay(player));
        }
        for(Gameplay gameInstance: game){
            pool.execute(gameInstance);
        }
    }
    

}
