package com.example;

import java.io.IOException;

/**
 * This class is responsible for starting the Player process and the Initiator process communicating using IPC(Inter-Process Communication).
 */

public class SeparateProcesses {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
         * The main method starts the Player process and the Initiator process.
         * Player 2 process listens for incoming connections on port 5000 and the Initiator process connects to the Player process on the same port.
         */
        System.out.println("Starting Player process...");
        ProcessBuilder serverProcessBuilder = new ProcessBuilder("java", "-cp", "target/classes", "com.example.PlayerProcess");
        serverProcessBuilder.inheritIO();
        Process serverProcess = serverProcessBuilder.start();

        Thread.sleep(1000);

        System.out.println("Starting Initiator process...");
        ProcessBuilder clientProcessBuilder = new ProcessBuilder("java", "-cp", "target/classes", "com.example.PlayerProcess", "initiator");
        clientProcessBuilder.inheritIO();
        Process clientProcess = clientProcessBuilder.start();

        System.out.println("-".repeat(92));
        System.out.println("Process ID\tPlayer\t\tAction\t\tMessage");
        System.out.println("-".repeat(92));

        serverProcess.waitFor();
        clientProcess.waitFor();
    }
}
