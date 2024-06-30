Text-based replica of the game Minesweeper, made using Java

Run the Java program and use the terminal to communicate with the program
Follow the instructions printed in the terminal

--- Program Structure ---
Structure of the main program goes as follows:
- Ask for user for input regarding dimensions of the board
- Initialize board with said dimensions, with a ratio of the spaces being landmines
- Execute the following until a win or lose condition is met:
  - Ask user for the square to perform an action upon (cannot be a space that is already revealed, since it is irrelevant)
  - Perform the action, checking whether that action leads to a win/lose condition
- When a win or lose condition is met, print out the minefield entirely revealed, and the corresponding message to win/lose

Structure of the class:
- Main program works with a class called Board, which is implemented in Board.java
- Board keeps track of the state of the board, and has a boolean 2D array of which tiles are mines, as well as a string 2D array of what the user sees
- Board has functions the main program calls to update the state of the board

--- Reflection ---
- Some parts of the program could be modularized, such as the part of asking for user input and validating the value
- Function and variable names are somewhat confusing, renaming could increase readability
