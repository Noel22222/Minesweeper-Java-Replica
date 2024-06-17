import java.util.*;
class Main {
  public static void main(String[] args) {
    
    Scanner scan = new Scanner(System.in);
    
    // input height of board
    System.out.println("Set height of the board");
    int height = Integer.parseInt(scan.nextLine());
    while (height <= 0){
      System.out.println("Invalid input. Please enter a positive integer to set as height.");
      height = Integer.parseInt(scan.nextLine());
    }
    
    // input length of board
    System.out.println("Set length of the board");
    int length = Integer.parseInt(scan.nextLine());
    while (length <= 0){
      System.out.println("Invalid input. Please enter a positive integer to set as length");
      length = Integer.parseInt(scan.nextLine());
    }

    //initialize condition states and board
    Board canada = new Board(height, length, (int)(length * height / 8));
    boolean lost = false;
    boolean win = false;
    
    //while neither conditions are met (game still in session)
    while (!lost && !win){
      
      System.out.println(canada.vRadar());
      
      //input height coordinate
      System.out.println("Row?");
      int row = Integer.parseInt(scan.nextLine());
      while (0 >= row || row > height){
        System.out.println("Invalid input. Must be between 1 and " + height + ".");
        row = Integer.parseInt(scan.nextLine());
      }
      //input length coordinate 
      System.out.println("Column?");
      int col = Integer.parseInt(scan.nextLine());
      while (0 >= col || col > length){
        System.out.println("Invalid input. Must be between 1 and " + length + ".");
        col = Integer.parseInt(scan.nextLine());
      }
      
      //user tries to perform action on revealed tile
      while (!(canada.getRadar(row-1, col-1).equals("-1")) && !(canada.getRadar(row-1, col-1).equals("x"))){
        System.out.println("You can't do anything with a revealed block!");
        System.out.println("Row?");
        row = Integer.parseInt(scan.nextLine());
        while (0 >= row || row > height){
          System.out.println("Invalid input. Must be between 1 and " + height + ".");
          row = Integer.parseInt(scan.nextLine());
        }
        System.out.println("Column?");
        col = Integer.parseInt(scan.nextLine());
        while (0 >= col || col > length){
          System.out.println("Invalid input. Must be between 1 and " + length + ".");
          col = Integer.parseInt(scan.nextLine());
        }
      }
      //user inputs action
      System.out.println("Action? (flag / reveal)");
      String action = scan.nextLine();
      //checks if action is valid
      while ((!action.equals("flag") && !action.equals("reveal")) || (action.equals("reveal") && canada.getRadar(row-1, col-1).equals("x"))){
        if (action.equals("reveal") && canada.getRadar(row-1, col-1).equals("x")){
          System.out.println("You can't reveal a flag. Remove flag by performing the flag actiona again");
        } else {
          System.out.println("Invalid action");
        }
        System.out.println("Action? (flag / reveal)");
        action = scan.nextLine();
      }
      
      //perform action, checks for losing condition
      if (action.equals("flag")){
        canada.flagRadar(row-1, col-1);
      } else {
        lost = canada.updateState(row-1, col-1);
      }
      //checks for winning condition
      if ((length * height) - canada.getRevealed() == (int)(length * height / 8)){
        win = true;
      }
    }

    //one of the conditions are met, game is over
    //prints out player's radar and actual minefield
    System.out.println(canada.vRadar());
    System.out.println(canada.mField());
    
    //prints ending response
    if (win){
      System.out.println("You Win!");
    } else if (lost){
      System.out.println("You Lost!");
    }
  }
}
