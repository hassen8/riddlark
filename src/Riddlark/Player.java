package Riddlark;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    int id;
    String state;
    String uname;
    String password;
    int gId;
    boolean logged;
    GameStats gameStats;
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public Player(String uname, String password, Socket socket, BufferedReader in, PrintWriter out) {
        this.uname = uname;
        this.password = password;
        this.logged = false;
        this.socket = socket;
        this.in = in;
        this.out = out;
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
