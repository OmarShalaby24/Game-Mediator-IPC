package com.example;

import java.io.*;
import java.net.*;

/**
 * PlayerProcess class is a client-server app that sends and receives messages between two processes.
 *      if the first argument is "initiator", then the process is an initiator
 *      if the first argument is anything else, then the process is a player ("player 2")
 * The server process ("player 2") listens for incoming connections on port 5000 and the client process("initiator") connects to the server on port 5000.
 * Once they connect, "initiator" sends a message to the "player 2".
 * "player 2" responds with the same message appended with a counter.
 * "initiator" then sends the received message back to "player 2" and the process continues until the counter reaches 10.
 */
public class PlayerProcess extends Player{
    /**
     * each process (player) has a message counter that increments with each message received
     * and a name that identifies the process as either a player or an initiator
     */
    private static final long processId = ProcessHandle.current().pid();

    public PlayerProcess(String name) {
        super(name);
    }
    static Player player;

    public static void main(String[] args) {
        // if the first argument is "initiator" then the process is an initiator
        if (args.length > 0 && args[0].equals("initiator")){
            player = new PlayerProcess("initiator");
            startClient();
        }
        // if the first argument is not "initiator" then the process is a player
        else{
            player = new PlayerProcess("player 2");
            startServer();
        }
    }

    public static void startClient() {
        try(Socket socket = new Socket("localhost", 5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            out.println("Hi");
            System.out.println("PID("+processId+") \t" + player.getName() + " \tSent: \t\tHi");
            String receivedMessage;
            while ((receivedMessage = in.readLine()) != null && player.getMessageCount() < 10){
                System.out.println("PID("+processId+") \t" + player.getName() + " \tReceived: \t" + receivedMessage);
                player.incrementMessageCount();
                System.out.println("PID("+processId+") \t" + player.getName() + " \tSent: \t\t" + receivedMessage + " " + player.getMessageCount());
                out.println(receivedMessage + " " + player.getMessageCount());
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void startServer(){
        try(ServerSocket serverSocket = new ServerSocket(5000);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            String receivedMessage;
            while((receivedMessage = in.readLine()) != null && player.getMessageCount() < 10){
                System.out.println("PID("+processId+") \t" + player.getName() + " \tReceived: \t" + receivedMessage);
                player.incrementMessageCount();
                System.out.println("PID("+processId+") \t" + player.getName() + " \tSent: \t\t" + receivedMessage + " " + player.getMessageCount());
                out.println(receivedMessage + " " + player.getMessageCount());
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
