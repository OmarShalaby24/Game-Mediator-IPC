package com.example;

abstract class Player {
    private final String name;
    private int messageCount;

    public Player(String name) {
        this.name = name;
        messageCount = 0;
    }

    public String getName() {
        return this.name;
    }
    public int getMessageCount(){
        return this.messageCount;
    }
    public void incrementMessageCount(){
        this.messageCount++;
    }
    
    public void printSentMessage(String message){
        System.out.println(this.name + " \tsent: \t\t" + message);
    }
    public void printReceivedMessage(String message){
        System.out.println(this.name + " \treceived: \t" + message);
    }
}
