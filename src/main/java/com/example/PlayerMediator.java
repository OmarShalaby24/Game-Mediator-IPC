package com.example;

/**
 * PlayerMediator(Concrete Mediator) is a class implementing the Mediator interface.
 * It is responsible for sending and receiving messages between players.
 */

public class PlayerMediator implements Mediator {
    @Override
    public void sendMessage(Player sender, Player recipient, String message) {
        sender.printSentMessage(message);
        recipient.receiveMessage(sender, message);
    }

    @Override
    public void receiveMessage(Player sender, Player recipient, String message) {
        if(recipient.getMessageCounter() <= 10){
            recipient.printReceivedMessage(message);
            String response = message + " " + recipient.getMessageCounter();
            recipient.sendMessage(sender, response);
        }
    }
}
