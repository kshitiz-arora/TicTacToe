import java.util.Scanner;

public class TicTacToe {
    
    public static void main(String[] args)
    {
        Scanner inp = new Scanner(System.in);

        System.out.println("Welcome to the game of TicTacToe!\n");
        System.out.println("If you want to play multiplayer version, enter 1. If you want to play with computer, enter 2. ");
        
        // input for type of game
        // (1) player vs player 
        // (2) player vs computer 
        int gameType = inp.nextInt();

        while (gameType!=1 && gameType!=2)
        {
            System.out.print("\nPlease enter a valid input: ");
            gameType = inp.nextInt();
        }

        // instructions
        System.out.println("");

        System.out.println("The numbers allocated to the boxes are as follows:\n");
        System.out.println("1 2 3\n4 5 6\n7 8 9\n");

        System.out.println("In your turn, enter the number corresponding to the box where you want to place your symbol.\n");

        // create instance of game board
        Board gameBoard = new Board();
        int currentPlayer;

        // Player vs Player
        if (gameType == 1)
        {
            System.out.println("Player 1: X\tPlayer 2: O\n");

            while (true)
            {
                currentPlayer = (gameBoard.getCurrentPlayer() == 1) ? 1 : 2;

                System.out.print("Player " + currentPlayer + ", enter desired box number: ");

                int boxNumber = inp.nextInt();
                while ((boxNumber > 9) || (boxNumber < 1))
                {
                    System.out.print("\nPlease enter an input within the range 1-9: ");
                    boxNumber = inp.nextInt();
                }

                boolean isFinished = gameBoard.playMove1v1(boxNumber-1);
                System.out.println();

                if (isFinished)
                {
                    break;
                }
            }
        }

        // Player vs Computer
        else if (gameType == 2)
        {
            System.out.println("Player: X\tComputer: O\n");

            while (true)
            {
                System.out.print("Enter desired box number: ");

                int boxNumber = inp.nextInt();
                while ((boxNumber > 9) || (boxNumber < 1))
                {
                    System.out.print("\nPlease enter an input within the range 1-9: ");
                    boxNumber = inp.nextInt();
                }

                boolean isFinished = gameBoard.playMove1vC(boxNumber-1);
                System.out.println();

                if (isFinished)
                {
                    break;
                }
            }
        }
        
        inp.close();
    }
}
