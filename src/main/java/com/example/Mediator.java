package com.example;

/**
 * The Mediator interface defines the contract for a mediator in the mediator design pattern.
 * It is responsible for controlling and facilitating the communication between different
 * components (colleagues) in the system. This pattern helps to reduce the direct dependencies
 * between components, promoting loose coupling and easier maintainability.
 */

public interface Mediator {
    void sendMessage(PlayerClass sender, PlayerClass recipient, String message);
    void receiveMessage(PlayerClass sender, PlayerClass recipient, String message);
}
