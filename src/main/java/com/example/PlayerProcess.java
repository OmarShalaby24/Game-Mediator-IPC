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
public class PlayerProcess {
    /**
     * each process (player) has a message counter that increments with each message received
     * and a name that identifies the process as either a player or an initiator
     */
    private static int messageCounter = 0;
    private static String name;
    private static final long processId = ProcessHandle.current().pid();
    public static void main(String[] args) {
        // if the first argument is "initiator" then the process is an initiator
        if (args.length > 0 && args[0].equals("initiator")){
            name = "initiator";
            startClient();
        }
        // if the first argument is not "initiator" then the process is a player
        else{
            name = "player 2";
            startServer();
        }
    }

    public static void startClient() {
        try(Socket socket = new Socket("localhost", 5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            out.println("Hi");
            System.out.println("PID("+processId+") \t" + name + " \tSent: \t\tHi");
            String receivedMessage;
            while ((receivedMessage = in.readLine()) != null && messageCounter < 10){
                System.out.println("PID("+processId+") \t" + name + " \tReceived: \t" + receivedMessage);
                messageCounter++;
                System.out.println("PID("+processId+") \t" + name + " \tSent: \t\t" + receivedMessage + " " + messageCounter);
                out.println(receivedMessage + " " + messageCounter);
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void startServer(){
        try(ServerSocket serverScoket = new ServerSocket(5000);
            Socket clientSocket = serverScoket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            String receivedMessage;
            while((receivedMessage = in.readLine()) != null && messageCounter < 10){
                System.out.println("PID("+processId+") \t" + name + " \tReceived: \t" + receivedMessage);
                messageCounter++;
                System.out.println("PID("+processId+") \t" + name + " \tSent: \t\t" + receivedMessage + " " + messageCounter);
                out.println(receivedMessage + " " + messageCounter);
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
