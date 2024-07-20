package com.example;

import java.io.IOException;

/**
 * Main class is responsible for starting the CommonProcess and SeparateProcesses classes.
 */

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
    // Both players instances on the same process
        System.out.println("\n"+"-".repeat(50)+"Part 1"+"-".repeat(50));
        System.out.println("Starting a common process (2 players on the same process)");
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "com.example.CommonProcess");
        pb.inheritIO();
        Process process = pb.start();
        process.waitFor();

        System.out.println("\n"+"-".repeat(50)+"Part 2"+"-".repeat(50));

    // Each player instance on a separate process communicating using IPC(Inter-Process Communication)
        System.out.println("Starting the separate processes (2 players on separate processes)");
        ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "target/classes", "com.example.SeparateProcesses");
        pb2.inheritIO();
        Process process2 = pb2.start();
        process2.waitFor();




    }
}