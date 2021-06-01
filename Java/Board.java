public class Board 
{    
    private Box[] boxes = new Box[9];
    private int currentPlayer = 1;
    private int boxesFilled = 0;

    // Constructor
    public Board()
    {
        this.boxes[0] = new Box();
        this.boxes[1] = new Box();
        this.boxes[2] = new Box();
        this.boxes[3] = new Box();
        this.boxes[4] = new Box();
        this.boxes[5] = new Box();
        this.boxes[6] = new Box();
        this.boxes[7] = new Box();
        this.boxes[8] = new Box();
    }

    // Private methods

    // setter for value in a box on board
    private void setEntry(int boxNumber, int value)
    {
        this.boxes[boxNumber].setValue(value);
    }

    // returns sum of values in a row
    private int getRowScore(int rowNumber)
    {
        return (this.boxes[3*rowNumber + 0].getValue() + 
                this.boxes[3*rowNumber + 1].getValue() + 
                this.boxes[3*rowNumber + 2].getValue());
    }

    // returns sum of values in a column
    private int getColumnScore(int columnNumber)
    {
        return (this.boxes[columnNumber + 0].getValue() +
                this.boxes[columnNumber + 3].getValue() +
                this.boxes[columnNumber + 6].getValue());
    }

    // returns sum of values in 1st diagonal
    private int getDiagonal1Score()
    {
        return (this.boxes[0].getValue() +
                this.boxes[4].getValue() +
                this.boxes[8].getValue());
    }

    // returns sum of values in 2nd diagonal
    private int getDiagonal2Score()
    {
        return (this.boxes[2].getValue() +
                this.boxes[4].getValue() +
                this.boxes[6].getValue());
    }

    // checker
    private boolean isGameWon(int boxNumber, int value)
    {
        int rowNumber = boxNumber / 3;
        int rowScore = getRowScore(rowNumber);
        if (rowScore == 3*value)
        {
            return true;
        }

        int columnNumber = boxNumber % 3;
        int columnScore = getColumnScore(columnNumber);
        if (columnScore == 3*value)
        {
            return true;
        }

        if (boxNumber % 4 == 0)
        {
            int diagonalScore = getDiagonal1Score();
            if (diagonalScore == 3*value)
            {
                return true;
            }
        }
        else if (boxNumber % 2 == 0)
        {
            int diagonalScore = getDiagonal2Score();
            if (diagonalScore == 3*value)
            {
                return true;
            }
        }
        
        return false;
    }

    // for computer's move

    // :: check if computer or player can win in this move
    private int winOrBlockMove()
    {
        for (int type = 0; type < 2; type++)
        {
            int check = (type == 0) ? 20 : 2;

            for (int i = 0; i < 3; i++)
            {
                if (getRowScore(i) == check)
                {
                    for (int j = 3*i; j < 3*(i+1); j++)
                    {
                        if (this.boxes[j].getValue() == 0)
                        {
                            return j;
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++)
            {
                if (getColumnScore(i) == check)
                {
                    for (int j = i; j < 9; j+=3)
                    {
                        if (this.boxes[j].getValue() == 0)
                        {
                            return j;
                        }
                    }
                }
            }
            if (getDiagonal1Score() == check)
            {
                for (int i = 0; i <= 8; i+=4)
                {
                    if (this.boxes[i].getValue() == 0)
                    {
                        return i;
                    }
                }
            }
            if (getDiagonal2Score() == check)
            {
                for (int i = 2; i <= 6; i+=2)
                {
                    if (this.boxes[i].getValue() == 0)
                    {
                        return i;
                    }
                }
            }
        }
        
        return -1;
    }

    // :: returns optimal move
    private int optimalMove()
    {
        int requiredMove = winOrBlockMove();
        if (requiredMove != -1)
        {
            return requiredMove;
        }

        // cases for 2nd move
        if (this.boxesFilled == 1)
        {
            if (this.boxes[4].getValue() == 0)
            {
                return 4;
            }
            else
            {
                return 0;
            }
        }

        // cases for 4th move
        else if (this.boxesFilled == 3)
        {
            if (getDiagonal1Score() == 12 || getDiagonal2Score() == 12)
            {
                return 1; 
            }
            
            if (getColumnScore(1) == 12 || getRowScore(1) == 12)
            {
                return 0; 
            }

            if (this.boxes[4].getValue() == 10)
            {
                if (this.boxes[1].getValue() == 1 && this.boxes[3].getValue() == 1)
                {
                    return 0;
                }
                if (this.boxes[1].getValue() == 1 && this.boxes[5].getValue() == 1)
                {
                    return 2;
                }
                if (this.boxes[3].getValue() == 1 && this.boxes[7].getValue() == 1)
                {
                    return 6;
                }
                if (this.boxes[5].getValue() == 1 && this.boxes[7].getValue() == 1)
                {
                    return 8;
                }

                if (this.boxes[1].getValue() == 1)
                {
                    if (this.boxes[6].getValue() == 1)
                    {
                        return 0;
                    }
                    if (this.boxes[8].getValue() == 1)
                    {
                        return 2;
                    }
                }
                if (this.boxes[3].getValue() == 1)
                {
                    if (this.boxes[2].getValue() == 1)
                    {
                        return 0;
                    }
                    if (this.boxes[8].getValue() == 1)
                    {
                        return 6;
                    }
                }
                if (this.boxes[5].getValue() == 1)
                {
                    if (this.boxes[0].getValue() == 1)
                    {
                        return 2;
                    }
                    if (this.boxes[6].getValue() == 1)
                    {
                        return 8;
                    }
                }
                if (this.boxes[7].getValue() == 1)
                {
                    if (this.boxes[0].getValue() == 1)
                    {
                        return 6;
                    }
                    if (this.boxes[2].getValue() == 1)
                    {
                        return 8;
                    }
                }
            }
            
            if (this.boxes[4].getValue() == 1)
            {
                if (getDiagonal1Score() == 12)
                {
                    return 2;
                }
                if (getDiagonal2Score() == 12)
                {
                    return 0;
                }
            }
        }

        // cases for 6th move
        else if (this.boxesFilled == 5)
        {
            if (getColumnScore(1) == 21 && (getRowScore(0) == 12 || getRowScore(2) == 12))
            {
                return 3;
            }
            if (getRowScore(1) == 21 && (getColumnScore(0) == 12 || getColumnScore(2) == 12))
            {
                return 1;
            }

            if (getDiagonal1Score() == 21)
            {
                if (this.boxes[1].getValue() == 1 && this.boxes[3].getValue() == 1)
                {
                    return 2;
                }
                if (this.boxes[5].getValue() == 1 && this.boxes[7].getValue() == 1)
                {
                    return 2;
                }
            }
            if (getDiagonal2Score() == 21)
            {
                if (this.boxes[1].getValue() == 1 && this.boxes[5].getValue() == 1)
                {
                    return 0;
                }
                if (this.boxes[3].getValue() == 1 && this.boxes[7].getValue() == 1)
                {
                    return 0;
                }
            }

            if (getDiagonal1Score() == 12)
            {
                if (getColumnScore(1) == 12)
                {
                    if (this.boxes[0].getValue() == 1)
                    {
                        return 6;
                    }
                    else
                    {
                        return 2;
                    }
                }
                if (getRowScore(1) == 12)
                {
                    if (this.boxes[0].getValue() == 1)
                    {
                        return 2;
                    }
                    else
                    {
                        return 6;
                    }
                }
            }
            if (getDiagonal2Score() == 12)
            {
                if (getColumnScore(1) == 12)
                {
                    if (this.boxes[2].getValue() == 1)
                    {
                        return 8;
                    }
                    else
                    {
                        return 0;
                    }
                }
                if (getRowScore(1) == 12)
                {
                    if (this.boxes[2].getValue() == 1)
                    {
                        return 0;
                    }
                    else
                    {
                        return 8;
                    }
                }
            }
        }
        
        // cases for 8th move
        if (this.boxesFilled == 7)
        {
            for (int i = 0; i < 9; i++)
            {
                if (this.boxes[i].getValue() == 0)
                {
                    return i;
                }
            }
        }        

        return -1;
    }


    // Public methods

    // getter for currentPlayer
    public int getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    // display game board
    public void showBoard()
    {
        System.out.println(this.boxes[0].getSymbol() + " " + 
                            this.boxes[1].getSymbol() + " " + 
                            this.boxes[2].getSymbol());
        System.out.println(this.boxes[3].getSymbol() + " " + 
                            this.boxes[4].getSymbol() + " " + 
                            this.boxes[5].getSymbol());
        System.out.println(this.boxes[6].getSymbol() + " " + 
                            this.boxes[7].getSymbol() + " " + 
                            this.boxes[8].getSymbol());
    }

    // method for player vs player move
    public boolean playMove1v1(int boxNumber)
    {
        // check if box is available
        if (this.boxes[boxNumber].getValue() != 0)
        {
            System.out.println("This box has already been filled. Choose another.");
            return false;
        }

        setEntry(boxNumber, this.currentPlayer);
        this.boxesFilled++;

        System.out.println("");
        showBoard();

        // check game status
        boolean status = isGameWon(boxNumber, this.currentPlayer);

        // player won
        if (status)
        {
            int winner = (this.currentPlayer == 1) ? 1 : 2;
            System.out.println("\nPlayer " + winner + " won.");
        }

        // draw game
        if (boxesFilled == 9 && !status)
        {
            System.out.println("\nGame drawn. No player won.");
            status = true;
        }

        this.currentPlayer = (this.currentPlayer == 1) ? 10 : 1;

        return status;
    }


    // method for player vs computer move
    public boolean playMove1vC(int boxNumber)
    {
        // check if box is available
        if (this.boxes[boxNumber].getValue() != 0)
        {
            System.out.println("This box has already been filled. Choose another.");
            return false;
        }

        setEntry(boxNumber, this.currentPlayer);
        this.boxesFilled++;
        System.out.println("");
        showBoard();

        // check status after player's move
        boolean status = isGameWon(boxNumber, this.currentPlayer);

        // player won
        if (status)
        {
            System.out.println("\nPlayer won.");
        }

        // game drawn
        if (this.boxesFilled == 9 && !status)
        {
            System.out.println("\nGame drawn.");
            status = true;
        }

        // computer's move
        if (!status)
        {
            this.currentPlayer = 10;
            
            // get optimal move for computer
            boxNumber = optimalMove();
            System.out.println("\nThe computer chose: " + (boxNumber+1));

            setEntry(boxNumber, this.currentPlayer);
            this.boxesFilled++;
            System.out.println("");
            showBoard();

            // check status after computer's move
            status = isGameWon(boxNumber, this.currentPlayer);
            
            // computer won
            if (status)
            {
                System.out.println("\nComputer won.");
            }
            this.currentPlayer = 1;
        }

        return status;
    }
}
