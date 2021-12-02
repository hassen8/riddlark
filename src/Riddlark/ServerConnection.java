/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Riddlark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author hsn
 */
public class ServerConnection implements Runnable {

    private Socket clientReceiver;
    private BufferedReader input;

    public ServerConnection(Socket s) throws IOException {
        clientReceiver = s;
        input = new BufferedReader(new InputStreamReader(clientReceiver.getInputStream()));

    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverResponse = input.readLine();
                if (serverResponse == null) {
                    System.out.println("Server Disconnected");
                    break;
                }
                System.out.println(serverResponse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                clientReceiver.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
