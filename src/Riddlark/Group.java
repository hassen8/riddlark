package Riddlark;

import java.util.ArrayList;

public class Group {

    int id;
    int playerNo = 0;
    boolean isFull;
    boolean isReady;
    boolean inSession;
    Player[] players = new Player[4];

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

    public int getPlayerNo() {
        return playerNo;
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

}
