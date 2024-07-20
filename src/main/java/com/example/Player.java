package com.example;

/**
 * Player class(Concrete Colleague) is responsible to create instance of player
 * in construction. it assigns name, and mediator to the player and set the messageCount to 0
 * when player wants to send/receive a message. he communicates with the mediator to send the message
 */

public class Player {
    private final String name;
    private int messageCounter;
    private final Mediator mediator;

    public Player(String name, Mediator mediator){
        this.name = name;
        this.messageCounter = 0;
        this.mediator = mediator;
    }

    public int getMessageCounter() {
        return messageCounter;
    }

    public void sendMessage(Player recipient, String message){
        this.mediator.sendMessage(this, recipient, message);
    }


    public void receiveMessage(Player sender, String message){
        this.messageCounter++;
        mediator.receiveMessage(sender, this, message);
    }

    public void printSentMessage(String message){
        System.out.println(this.name + " \tsent: \t\t" + message);
    }
    public void printReceivedMessage(String message){
        System.out.println(this.name + " \treceived: \t" + message);
    }
}
