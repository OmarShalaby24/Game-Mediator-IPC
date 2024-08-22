## **Problem:**
Given a Player class - an instance of which can communicate with other Players.

#### **The requirements are as follows:**

1. Create 2 Player instances
2. One of the players should send a message to second player (let's call this player "initiator")
3. When a player receives a message, it should reply with a message that contains the received message concatenated with the value of a counter holding the number of messages this player already sent.
4. Finalize the program (gracefully) after the initiator sent 10 messages and received back 10 messages (stop condition)
5. Both players should run in the same java process (strong requirement)
6. Document for every class the responsibilities it has.
7. Additional challenge (nice to have) opposite to 5: have every player in a separate JAVA process.


# **Class-Interface Definitions:**
#### **CommonProcess:**
 * This class is responsible for creating 2 Player instances on the same process.
   * This Class creates a mediator and two Player instances on the same process.
   * The mediator is responsible for communication between players.
 
#### **Player:**
 * Abstract class for player containing common properties and methods.
 
#### **Mediator:**
 *  The Mediator interface defines the contract for a mediator in the mediator design pattern.
 
#### **PlayerClass:**
 * Player class(Concrete Colleague) is responsible to create instance of player in construction. it assigns name, and mediator to the player and set the messageCount to 0
 * when player wants to send/receive a message. he communicates with the mediator to send the message
 
#### **PlayerMediator:**
 * PlayerMediator(Concrete Mediator) is a class implementing the Mediator interface.
 * It is responsible for sending and receiving messages between players.

#### **SeparateProcesses:**
 * This class is responsible for starting the Player process and the Initiator process communicating using IPC(Inter-Process Communication).
   * Player 2 process listens for incoming connections on a certain port and the Initiator process connects to the Player process on the same port.

#### **PlayerProcess:**
 * PlayerProcess class is a client-server app that sends and receives messages between two processes.
   * if the first argument is "initiator", then the process is an initiator
   * if the first argument is anything else, then the process is a player ("player 2")
 * The server process ("player 2") listens for incoming connections on port 5000 and the client process("initiator") connects to the server on port 5000.
 * Once they connect, "initiator" sends a message to the "player 2".
 * "player 2" responds with the same message appended with a counter.
 * "initiator" then sends the received message back to "player 2" and the process continues until the counter reaches 10.

#### **Main:**
 * Main class is responsible for starting the CommonProcess and SeparateProcesses classes.
 
# **How to run?**

Just execute run.sh

    ./run.sh

If you are using linux you might need to give permission to run.sh file. You can do that by running the following command in the terminal:

    sudo chmod +x run.sh    
