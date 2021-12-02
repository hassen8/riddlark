/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Riddlark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author hsn
 */
public class Client {

    private static final int PORT = 9090;
    private static final String SERVER_IP = "127.0.0.1";
    private static boolean firstBoot = true;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, PORT);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ServerConnection serverConn = new ServerConnection(socket);

        new Thread(serverConn).start();

        while (true) {
            if (firstBoot == true) {
                System.out.println("Welcome to Riddlark...\nType register to create a user\n"
                        + "Or Type 'login' if you have one\n"
                        + "You can quit the game at any time by typing 'quit'\n>");
                firstBoot = false;
            } else {
                System.out.println(">");
            }

            String command = keyboard.readLine();
            if (command.equals("quit")) {
                break;
            }

            out.println(command);
        }
        System.out.println("Bye Bye...");

        out.close();
        keyboard.close();
        socket.close();
        System.exit(0);
    }
}
