public class Box {
    public static final int DEFAULT_VAL = 0;
    private int value;

    // Constructor
    public Box() 
    {
        this.value = DEFAULT_VAL;
    }

    // Public methods
    
    // getters
    public int getValue()
    {
        return this.value;
    }

    public String getSymbol()
    {
        if (this.value == 1)
        {
            return "X";
        }
        else if (this.value == 10)
        {
            return "O";
        }
        else
        {
            return "-";
        }
    }

    // setter for value stored in an instance
    public void setValue(int value)
    {
        // we have assumed value 1 for X and 10 for O
        this.value = value;
    }
}
