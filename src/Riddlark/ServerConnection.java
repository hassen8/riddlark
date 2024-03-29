package Riddlark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
                    System.out.println("Server has Disconnected");
                    Client.serverConnection = "disconnected";
                    break;
                }
                System.out.println(serverResponse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
