import java.util.Scanner;
public class Player {
    private Scanner s = new Scanner(System.in);
    private String name;
    private char symbol;

    public Player(String name, char symbol){
        System.out.println("Enter your name: ");
        name = s.nextLine();
        this.name = name;
        this.symbol = symbol;
    }

    public String getName(){ 
        return name;
    }

    public char getSymbol(){
        return symbol;
    }
}
