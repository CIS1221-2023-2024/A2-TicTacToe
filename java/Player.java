public class Player {
    private String name;
    private char symbol;

    // player constructor
    public Player(String name, char symbol){
        this.name = name;
        this.symbol = symbol;
    }
    // name getter
    public String getName(){ 
        return name;
    }

    // symbol getter
    public char getSymbol(){
        return symbol;
    }
}
