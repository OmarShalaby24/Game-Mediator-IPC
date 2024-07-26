package com.example;

/**
 * Player class(Concrete Colleague) is responsible to create instance of player
 * in construction. it assigns name, and mediator to the player and set the messageCount to 0
 * when player wants to send/receive a message. he communicates with the mediator to send the message
 */

public class PlayerClass extends Player {
    private final Mediator mediator;

    public PlayerClass(String name, Mediator mediator){
        super(name);
        this.mediator = mediator;
    }

    public void sendMessage(PlayerClass recipient, String message){
        this.mediator.sendMessage(this, recipient, message);
    }

    public void receiveMessage(PlayerClass sender, String message){
        this.incrementMessageCount();
        mediator.receiveMessage(sender, this, message);
    }

}
