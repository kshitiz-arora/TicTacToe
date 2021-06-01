Course: CS202 Lab 2 (2019CSB1095)

Title: Tic Tac Toe (Java programming assignment)
Description: Java program to play the game of TicTacToe using OOP.


Files:
>> TicTacToe.java/class
>> Board.java/class
>> Box.java/class

TicTacToe.java: Driver program which deals with user input and calls
                instance of Board object and instructs it to play
                moves according to input.

Board.java: Program for the Board object which imitates a real board.
            It contains all important methods required for the game 
            to proceed.
            The board constructor calls instances of Box object from
            Box.java 

Box.java:   Program for the Box object which is a part of Board object
            in Board.java


Instructions/Explanation:

To run code using terminal:
>> javac TicTacToe.java
>> java TicTacToe

The board will be displayed as shown below:

X - -
- - O
O - X

Here, X denotes that the box is filled by Player1/Player,
O denotes that the box is filled by Player2/Computer
and, - denotes that the box is empty.

The boxes are denoted by numbers as follows:
1 2 3 
4 5 6
7 8 9

So, user needs to input the box number corresponding to the 
position it wants to play turn.


Now, the program would require you to input the type of game
you want to play.
Enter 1 for Player vs Player
Enter 2 for Player vs Computer


Player vs Player:

Player 1 (X) and Player 2 (O) have to enter the input 
alternatively to play turn.

After each turn, the program checks to see if the game has 
been won by either player or if it has been drawn.
If not, the game continues.

The game ends with either player winning, or all the boxes 
being filled withoutany result. Hence, draw.


Player vs Computer:

Player (X) has to enter the input to play turn.

Then, Computer (O) will play an optimal move.
The optimal move is calculated as follows:
    -First the program looks for a chance of Computer winning.
    -Then, it looks for a move to block Player from winning.
    -As soon as a condition matches, it plays the move.
    -If neither condition matches, it plays move according to
    some pre defined rules for 2nd, 4th, 6th, and 8th move.

According to the rules, the Player will almost never win.
Maybe an exception, if any case is missed.

Here also, after each move by Player or Computer, the program
checks game status (won/drawn) and continues execution of 
program accordingly.