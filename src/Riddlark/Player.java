package Riddlark;

import java.net.Socket;

public class Player {

    int id;
    String state;
    String uname;
    String password;
    boolean logged;
    Socket socket;
    int gId;
    GameStats gameStats;

    public Player(String uname, String password, Socket socket) {
        this.uname = uname;
        this.password = password;
        this.logged = false;
        this.socket = socket;
        id++;
    }

    public int getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStats() {
        return "Name: " + getUname()
                + "\nCorrect Guesses: " + gameStats.getCorrectGuesses()
                + "\nTime taken to answer last riddle: " + gameStats.getLastElapsedTime() + " seconds";
    }
    public void setStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }
}
