package com.example;

/**
 * This class is responsible for creating 2 Player instances on the same process.
 */

public class CommonProcess {
    public static void main(String[] args) {
        /*
         * This Class creates a mediator two Player instances on the same process.
         * the mediator is responsible for communication between players.
         */
        System.out.println("PID: "+ProcessHandle.current());
        System.out.println("-".repeat(76));
        System.out.println("Sender\t\tAction\t\tMessage");
        System.out.println("-".repeat(76));

        PlayerMediator mediator = new PlayerMediator();
        Player player1 = new Player("initiator", mediator);
        Player player2 = new Player("player 2", mediator);

        player1.sendMessage(player2, "Hi");
    }
}
