package Riddlark;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 9090;
    private static ExecutorService pool = Executors.newFixedThreadPool(30);

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        while (true) {
            System.out.println("[SERVER] is waiting for clients to connect....");
            Socket client = listener.accept();
            System.out.println("[SERVER] is connected to a client....");
            ClientHandler clientThread = new ClientHandler(client);
            pool.execute(clientThread);
        }
    }
}
