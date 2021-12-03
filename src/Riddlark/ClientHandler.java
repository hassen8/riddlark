package Riddlark;

import static Riddlark.Server.allPlayers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;


public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private Player player = null;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
    }
    @Override
    public void run() {
        try {
            while(true){            
            String request = in.readLine(); // reads from the client socket
            if(request==null) break; // if socket is closed it escapes the loop
            
            if(request.contains("register")){ 
            registerPlayer(); // To handle registration for the client
            }
            
            else if(request.contains("login")){  
              if(player==null){   // check if player is registered
              out.println("You must register first. Type 'register' to register.");  // Sends output to client socket
              }
              else if(player.isLogged()==true){  // check if player is already logged in
              out.println("You're already logged in, Type 'ready' to start playing the game");
              }
              else{
              loginPlayer(); // To handle login for the client
              }
            }
            else if(request.contains("ready")){  
              if(player !=null && player.isLogged()!=true || player==null){
              out.println("You must login first. Type 'login' to log in.");
              }else if(player!=null && player.isLogged()==true){
              startGame();
              }
            }else{
             out.println("Input not recognized... try again");
            }
            }
            
            client.close();  // closes this socket

        } catch (IOException e) { // To catch and handle exceptions
            System.out.println("Exception in client handler "+e);
        }finally{
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                System.out.println("Exception in closing resources "+e);
            }
        }
    }
    
    String uname;
    String password;
    private void registerPlayer() throws IOException{
        String control="none";
        while(uname==null||password==null){
        if(uname==null){
        if(control.equals("none")){
        out.println("Enter username");
        }
        String cName = in.readLine();
        if(PlayerBase.checkPlayer(cName)!=null){
        out.println("Username Taken, Try a different username");
        control="tryAgain";
        }else{
        uname=cName;
        if(password==null){
        out.println("Enter password");
        password = in.readLine();
           }
          }
         }
        }
        player = new Player(uname,password,client);
        PlayerBase.addPlayer(player);
        allPlayers.add(player);
        out.println("Registration Successfull, Type 'login' to continue");
        uname=null;
        password=null;
    }
    
    private void loginPlayer() throws IOException{
        String control="none";
        while(uname==null||password==null){
        if(uname==null){
        if(control.equals("none")){
        out.println("Enter username");
        }
        String cName = in.readLine();
        if(PlayerBase.checkPlayer(cName)==null){
        out.println("Username not found, Try entering your correct username");
        control="tryAgain";
        }else{
        uname=cName;
        if(password==null){
        out.println("Enter password");
        password = in.readLine();
           }
          }
         }
        }
       if(player.getUname().equals(uname) && player.getPassword().equals(password)){
       player.setLogged(true);
       out.println("You have successfully logged in as > "+player.getUname()+". Type 'ready' to start playing");
       }else{
       out.println("Login failed, Type 'login' to try again");
       }
       uname=null;
       password=null;
    }

    private void startGame() {
        out.println("Get ready");
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 5;
            public void run() {
                out.println("Game starts in ..." + i--);
                if (i< 0){
                    timer.cancel();
                }
            }
        }, 0, 1000);
        
    }

}
            
