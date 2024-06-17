public class Board{
  //minefield: true if mine, false if not
  private boolean[][] minefield;
  //what the player sees ; revealed tiles
  private String[][] radar;
  //number of blocks revealed
  private int revealed;
  
  //initialize board
  public Board(int width, int length, int nMines){
    //initalizes boards
    minefield = new boolean[width][length];
    radar = new String[width][length];
    revealed = 0;
    int mineCount = 0;
    //plant mines
    while (mineCount < nMines){
      int randomRow = (int)(Math.random() * width);
      int randomColumn = (int)(Math.random() * length);
      if (!minefield[randomRow][randomColumn]){
        minefield[randomRow][randomColumn] = true;
        mineCount++;
      }
    }
    //empties radar
    for (int r = 0; r < width; r++){
      for (int c= 0; c < length; c++){
        radar[r][c] = "-1";
      }
    }
  }

  //updates state of tile, adjacent tiles if 0
  public boolean updateState(int row, int column){
    int danger = dangerCount(row, column);
    //updates radar
    radar[row][column] = "" + danger;
    if (danger == 9){
      //hit a bomb
      return true;
    } else if (danger != 0){
      //did not hit a bomb
      revealed++;
      return false;
    } else {
      //if 0 bombs nearby, reveal adjacent blocks
      //sets up boundaries of area to update
      int startingRow = row - 1;
      int startingColumn = column - 1;
      int endingRow = row + 1;
      int endingColumn = column + 1;
      
      if (startingRow < 0){
        startingRow = row;
      }
      if (endingRow >= minefield.length){
        endingRow = row;
      }
      if (startingColumn < 0){
        startingColumn = column;
      }
      if (endingColumn >= minefield[0].length){
        endingColumn = column;
      }
      
      //update adjacent blocks
      for (int r = startingRow; r <= endingRow; r++){
        for (int c = startingColumn; c <= endingColumn; c++){
          if (radar[r][c].equals("-1")){
            updateState(r, c);
          }
        }
      }
      //did not hit a bomb
      revealed++;
      return false;
    }
  }

  //counts danger level of tile
  public int dangerCount(int row, int column){
    //set up boundaries for checking
    int startingRow = row - 1;
    int startingColumn = column - 1;
    int endingRow = row + 1;
    int endingColumn = column + 1;
    
    if (startingRow < 0){
      startingRow = row;
    }
    if (endingRow >= minefield.length){
      endingRow = row;
    }
    if (startingColumn < 0){
      startingColumn = column;
    }
    if (endingColumn >= minefield[0].length){
      endingColumn = column;
    }
    int danger = 0;

    if (minefield[row][column]){
      return 9;
    } else {
      for (int r = startingRow; r <= endingRow; r++){
        for (int c = startingColumn; c <= endingColumn; c++){
          if (minefield[r][c]){
            danger++;
          }
        }
      }
    }
    return danger;
  }
  
  //Returns player's radar in format of a string
  public String vRadar(){
    String returned = "";
    for (int r = 1; r < minefield.length+1; r++){
      returned += r + "  ";
    }
    returned += "\n";
    for (int r = 0; r < minefield.length; r++){
      returned += r + 1 + "";
      for (int c = 0; c < minefield[0].length; c++){
        returned += "[";
        if (radar[r][c].equals("-1")){
          returned += " ";
        } else if (radar[r][c].equals("0")){
          returned += "·";
        } else {
          returned += "" + radar[r][c];
        }
        returned += "]";
      }
      returned += "\n";
    }
    return returned;
  }

  //Returns omni-radar in format of a string
  public String aRadar(){
    String returned = "";
    for (int r = 0; r < minefield.length; r++){
      for (int c = 0; c < minefield[0].length; c++){
        returned += " " + dangerCount(r, c) + " ";
      }
      returned += "\n";
    }
    return returned;
  }
  
  //Returns the actual minefield in true/false states
  public String vMinefield(){
    String returned = "";
    for (boolean row[] : minefield){
      for (boolean state : row){
        returned += state + " ";
      }
      returned += "\n";
    }
    return returned;
  }
  
  //Returns the actual minefield in o/x states
  public String mField(){
    String returned = "";
    for (boolean row[] : minefield){
      for (boolean mine : row){
        if (mine){
          returned += "[x]";
        } else {
          returned += "[ ]";
        }
      }
      returned += "\n";
    }
    return returned;
  }

  //Flags and unflags tile on player's radar
  public void flagRadar(int r, int c){
    if (radar[r][c].equals("x")){
      radar[r][c] = "-1";
    } else {
      radar[r][c] = "x";
    }
  }

  //Gets danger level of tile
  public String getRadar(int r, int c){
    return radar[r][c];
  }

  //Returns number of tiles revealed
  public int getRevealed(){
    return revealed;
  }
}
