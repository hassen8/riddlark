/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Riddlark;

import java.net.Socket;

public class Player {

    static int id = 0;
    String uname;
    String password;
    boolean logged;
    Socket socket;
    int gId;

    public Player(String uname, String password, Socket socket) {
        this.id = id + 1;
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

}
