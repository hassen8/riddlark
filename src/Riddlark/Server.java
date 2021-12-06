package Riddlark;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 9090;
    public static Player stagedPlayers[] = new Player[4];
    public static ArrayList<Player> allPlayers= new ArrayList<>();
    public static ArrayList<Group> groups;
    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        while (true) {
            System.out.println("[SERVER] is waiting for clients to connect....");
            Socket client = listener.accept();
            System.out.println("[SERVER] is connected to a client....");
            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}
