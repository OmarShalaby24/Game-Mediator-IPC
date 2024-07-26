package com.example;

/**
 * PlayerMediator(Concrete Mediator) is a class implementing the Mediator interface.
 * It is responsible for sending and receiving messages between players.
 */

public class PlayerMediator implements Mediator {
    @Override
    public void sendMessage(PlayerClass sender, PlayerClass recipient, String message) {
        sender.printSentMessage(message);
        recipient.receiveMessage(sender, message);
    }

    @Override
    public void receiveMessage(PlayerClass sender, PlayerClass recipient, String message) {
        if(recipient.getMessageCount() <= 10){
            recipient.printReceivedMessage(message);
            String response = message + " " + recipient.getMessageCount();
            recipient.sendMessage(sender, response);
        }
    }
}
